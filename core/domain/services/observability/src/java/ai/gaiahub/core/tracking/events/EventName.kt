package ai.gaiahub.core.tracking.events

/**
 * Represents the unique identifier for a tracking event.
 *
 * Provides type safety for event names that are sent to tracking providers.
 * Typically implemented as grouped enums for related events.
 *
 * ## Example Implementation
 * ```kotlin
 * enum class AuthEvents(override val value: String) : EventName {
 *     LOGIN_ATTEMPT("login_attempt"),
 *     LOGIN_SUCCESS("login_success"),
 *     LOGOUT("logout")
 * }
 * ```
 *
 * @see TrackingEvent
 */
interface EventName {

    /**
     * The string identifier sent to tracking providers.
     */
    val value: String
}