package ai.gaiahub.core.tracking.providers

import ai.gaiahub.core.models.tracking.ProviderType
import ai.gaiahub.core.tracking.events.TrackingEvent

/**
 * Abstraction for tracking providers that send events to external analytics services.
 *
 * Implementations handle provider-specific logic for transforming and transmitting
 * tracking events to platforms like Firebase Analytics, Crashlytics and etc.
 *
 * ## Example Implementation
 * ```kotlin
 * class FirebaseTrackingProvider(
 *     private val firebaseAnalytics: FirebaseAnalytics
 * ) : TrackingProvider {
 *
 *     override val type: ProviderType = ProviderTypeImpl.FIREBASE
 *
 *     override suspend fun trackEvent(event: TrackingEvent): Result<Unit> {
 *         return try {
 *             val bundle = createBundle(event.parameters)
 *             firebaseAnalytics.logEvent(event.name.value, bundle)
 *             Result.success(Unit)
 *         } catch (e: Exception) {
 *             Result.failure(e)
 *         }
 *     }
 * }
 * ```
 *
 * @see ai.gaiahub.core.models.tracking.ProviderType
 * @see TrackingEvent
 */
interface TrackingProvider {

    /**
     * Identifies the provider type for routing and configuration.
     */
    val type: ProviderType

    /**
     * Sends a tracking event to this provider's analytics service.
     *
     * @param event The tracking event to send to this provider
     * @return [Result.success] if the event was sent successfully,
     *         [Result.failure] with the exception if the operation failed
     */
    suspend fun trackEvent(event: TrackingEvent): Result<Unit>
    fun enableTracking()
    fun disableTracking()
}