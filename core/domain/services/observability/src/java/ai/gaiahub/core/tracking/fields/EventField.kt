package ai.gaiahub.core.tracking.fields

/**
 * Represents a field in a tracking event.
 *
 * Provides type safety for event field names and enables grouping related fields.
 * Typically implemented as grouped enums for related fields.
 *
 * ## Example Implementation
 * ```kotlin
 * enum class AuthFields(override val field: String) : EventField {
 *     EMAIL("email"),
 *     IS_SUCCESS("is_success"),
 *     ERROR("error")
 * }
 * ```
 *
 * @see ai.gaiahub.core.tracking.events.TrackingEvent
 */
interface EventField {

    /**
     * The string identifier sent to tracking providers.
     */
    val field: String
}