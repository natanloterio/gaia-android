package ai.gaiahub.core.tracking.routes

import ai.gaiahub.core.tracking.events.TrackingEvent
import ai.gaiahub.core.models.tracking.ProviderType

/**
 * Determines which tracking providers should handle specific events.
 *
 * Implementations define routing logic to map events to appropriate providers
 * based on event type, data sensitivity, or business requirements.
 *
 * ## Example Implementation
 * ```kotlin
 * class TrackingProviderRouterImpl : TrackingProviderRouter {
 *     private val eventProviderMapping = mapOf(
 *         TrackingEvent.LoginEvent::class to setOf(ProviderTypeImpl.FIREBASE, ProviderTypeImpl.CRASHLYTICS),
 *         TrackingEvent.ButtonClick::class to setOf(ProviderTypeImpl.FIREBASE)
 *     )
 *
 *     override fun getProviderTypesForEvent(event: TrackingEvent): Set<ProviderType> {
 *         return eventProviderMapping[event::class] ?: emptySet()
 *     }
 * }
 * ```
 *
 * @see ai.gaiahub.core.tracking.TrackingExecutor
 * @see ProviderType
 */
interface TrackingProviderRouter {

    /**
     * Returns the provider types that should handle the given event.
     *
     * @param event The tracking event to route
     * @return Set of provider types that should handle this event, empty if none
     */
    fun getProviderTypesForEvent(event: TrackingEvent): Set<ProviderType>
}