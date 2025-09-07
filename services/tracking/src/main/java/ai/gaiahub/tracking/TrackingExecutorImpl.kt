package ai.gaiahub.tracking

import ai.gaiahub.tracking.events.TrackingEvent
import ai.gaiahub.tracking.models.TrackingConfiguration
import ai.gaiahub.tracking.models.TrackingResult
import ai.gaiahub.tracking.providers.TrackingProvider
import ai.gaiahub.tracking.routes.TrackingProviderRouter

internal class TrackingExecutorImpl(
    private val trackingProviders: List<TrackingProvider>,
    private val configuration: TrackingConfiguration,
    private val providerRouter: TrackingProviderRouter
) : TrackingExecutor {
    
    private val enabledProviders: List<TrackingProvider>
        get() = trackingProviders.filter {
            configuration.enabledProviders.contains(it.type)
        }

    private fun getProvidersForEvent(event: TrackingEvent): List<TrackingProvider> {
        val targetProviderTypes = providerRouter.getProviderTypesForEvent(event)

        return enabledProviders.filter { provider ->
            provider.type in targetProviderTypes
        }
    }

    override suspend fun executeTracking(event: TrackingEvent): TrackingResult {
        val providers = getProvidersForEvent(event)
        if (providers.isEmpty()) {
            return TrackingResult.Success
        }

        val (successful, failed) = providers
            .map { it.type to it.trackEvent(event) }
            .partition { it.second.isSuccess }

        return when {
            failed.isEmpty() -> TrackingResult.Success

            successful.isEmpty() -> {
                val (providerType, failureResult) = failed.first()
                TrackingResult.Error(
                    exception = failureResult.exceptionOrNull() ?: Exception("Unknown error"),
                    provider = providerType
                )
            }

            else -> TrackingResult.PartialSuccess(
                successfulProviders = successful.map { it.first },
                failedProviders = failed.associate { (providerType, failureResult) ->
                    providerType to (failureResult.exceptionOrNull() ?: Exception("Unknown error"))
                }
            )
        }
    }
}