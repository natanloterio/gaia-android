package ai.gaiahub.core.domain.services.tracking.fields

enum class AuthFields(override val field: String) : EventField {
    EMAIL("email"),
    IS_SUCCESS("is_success"),
    ERROR("error")
}