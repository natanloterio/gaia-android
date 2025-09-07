package ai.gaiahub.core.domain.services.tracking.events.login

import ai.gaiahub.core.domain.services.tracking.events.EventName
import ai.gaiahub.core.domain.services.tracking.events.TrackingEvent
import ai.gaiahub.core.domain.services.tracking.fields.AuthFields
import ai.gaiahub.core.domain.services.tracking.fields.EventField

class LoginEvent(
    val email: String,
    val isSuccess: Boolean,
    val error: String? = null
) : TrackingEvent {
    override val name: EventName = LoginEvents.LOGIN_EVENT
    override val parameters: Map<EventField, Any?> =
        mapOf(
            AuthFields.EMAIL to email,
            AuthFields.IS_SUCCESS to isSuccess,
            AuthFields.ERROR to error
        )
    override val timestamp: Long = System.currentTimeMillis()
}