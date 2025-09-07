package ai.gaiahub.core.domain.services.tracking.events.login

import ai.gaiahub.core.domain.services.tracking.events.EventBuilder

abstract class LoginEventBuilder : EventBuilder() {
    abstract fun email(email: String): LoginEventBuilder
    abstract fun success(success: Boolean): LoginEventBuilder
    abstract fun error(error: String?): LoginEventBuilder

    abstract override suspend fun build(): LoginEvent
}