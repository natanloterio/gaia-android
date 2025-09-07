package ai.gaiahub.core.tracking

import ai.gaiahub.core.tracking.events.TrackingEvent
import ai.gaiahub.core.tracking.models.TrackingResult

/**
 * Orchestrates the execution of tracking events across multiple providers.
 *
 * Coordinates provider routing, configuration filtering, and result aggregation
 * to execute tracking operations and return comprehensive results.
 *
 * ## Example Implementation
 * ```kotlin
 * class TrackingExecutorImpl(
 *     private val trackingProviders: List<TrackingProvider>,
 *     private val configuration: TrackingConfiguration,
 *     private val providerRouter: TrackingProviderRouter
 * ) : TrackingExecutor {
 *
 *     override suspend fun executeTracking(event: TrackingEvent): TrackingResult {
 *         val targetProviders = getProvidersForEvent(event)
 *         val results = targetProviders.map { it.trackEvent(event) }
 *         return aggregateResults(results)
 *     }
 * }
 * ```
 *
 * @see ai.gaiahub.core.tracking.providers.TrackingProvider
 * @see ai.gaiahub.core.tracking.routes.TrackingProviderRouter
 */
interface TrackingExecutor {

    /**
     * Executes tracking for the given event across all applicable providers.
     *
     * @param event The tracking event to execute
     * @return Aggregated result from all targeted providers
     */
    suspend fun executeTracking(event: TrackingEvent): TrackingResult
}