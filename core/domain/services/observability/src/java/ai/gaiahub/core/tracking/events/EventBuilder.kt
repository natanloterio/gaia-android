package ai.gaiahub.core.tracking.events

/**
 * Base class for building and tracking events using the Builder pattern.
 *
 * Implementations provide chainable methods for setting event-specific parameters
 * before calling [build] to construct the tracking event.
 *
 * ## Example Implementation
 * ```kotlin
 * internal class LoginEventBuilderImpl : EventBuilder() {
 *     private var email: String = ""
 *     private var isSuccess: Boolean = false
 *
 *     fun email(email: String) = apply { this.email = email }
 *     fun success(isSuccess: Boolean) = apply { this.isSuccess = isSuccess }
 *
 *     override suspend fun build(): TrackingEvent {
 *         return TrackingEvent.LoginEvent(email, isSuccess)
 *     }
 * }
 * ```
 *
 * @see TrackingEvent
 */
abstract class EventBuilder {
    /**
     * Builds the tracking event with the configured parameters.
     * @return The constructed TrackingEvent
     */
    abstract suspend fun build(): TrackingEvent
}