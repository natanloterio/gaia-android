package ai.gaiahub.tracking.fields
enum class AuthFields(override val field: String) : EventField {
    EMAIL("email"),
    IS_SUCCESS("is_success"),
    ERROR("error")
}