package ai.gaiahub.core.tracking.events

import ai.gaiahub.core.tracking.fields.EventField

/**
 * Base class for all trackable events in the application.
 *
 * Sealed class that ensures type safety and exhaustive handling of tracking events.
 * Each implementation represents a specific user action or system occurrence.
 *
 * ## Example Implementation
 * ```kotlin
 * data class LoginEvent(
 *     val email: String,
 *     val isSuccess: Boolean
 * ) : TrackingEvent() {
 *     override val name: EventName = AuthEvents.LOGIN_ATTEMPT
 *     override val parameters: Map<EventField, Any?> = mapOf(
 *         AuthFields.EMAIL to email,
 *         AuthFields.IS_SUCCESS to isSuccess
 *     )
 *     override val timestamp: Long = System.currentTimeMillis()
 * }
 * ```
 *
 * @see EventName
 * @see EventField
 */
interface TrackingEvent {

    /**
     * The unique identifier for this event type, used by tracking providers.
     */
    abstract val name: EventName

    /**
     * Key-value pairs containing event-specific data to be tracked.
     */
    abstract val parameters: Map<EventField, Any?>

    /**
     * Timestamp in milliseconds when the event occurred.
     */
    abstract val timestamp: Long

    //Place events here
}