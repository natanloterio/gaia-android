package ai.gaiahub.tracking.routes

import ai.gaiahub.tracking.events.login.LoginEvent
import ai.gaiahub.tracking.events.uicomponents.ButtonClickEvent
import ai.gaiahub.tracking.providers.ProviderTypeImpl

/**
 * Factory for creating [TrackingProviderRouter] instances with predefined configurations.
 *
 * Provides production-ready router configurations with event-to-provider mappings.
 * Maps [ai.gaiahub.core.tracking.events.TrackingEvent] implementation classes to their target provider types.
 *
 * ## Example Usage
 * ```kotlin
 * val router = TrackingProviderRouterFactory.createDefaultRouter()
 * val executor = TrackingExecutorImpl(providers, configuration, router)
 * ```
 *
 * @see TrackingProviderRouter
 * @see TrackingProviderRouterImpl
 */
object TrackingProviderRouterFactory {

    /**
     * Creates a router with default production event-to-provider mappings.
     *
     * @return Router configured with standard provider assignments for each event type
     */
    fun createDefaultRouter(): TrackingProviderRouter {
        return TrackingProviderRouterImpl(
            eventProviderMapping = mapOf(
                LoginEvent::class to setOf(
                    ProviderTypeImpl.FIREBASE,
                    ProviderTypeImpl.CRASHLYTICS
                ),
                ButtonClickEvent::class to setOf(
                    ProviderTypeImpl.FIREBASE
                ),
            )
        )
    }
}