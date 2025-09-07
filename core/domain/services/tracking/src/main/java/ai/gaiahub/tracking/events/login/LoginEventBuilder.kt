package ai.gaiahub.tracking.events.login

import ai.gaiahub.tracking.events.EventBuilder

abstract class LoginEventBuilder : EventBuilder() {
    abstract fun email(email: String): LoginEventBuilder
    abstract fun success(success: Boolean): LoginEventBuilder
    abstract fun error(error: String?): LoginEventBuilder

    abstract override suspend fun build(): LoginEvent
}