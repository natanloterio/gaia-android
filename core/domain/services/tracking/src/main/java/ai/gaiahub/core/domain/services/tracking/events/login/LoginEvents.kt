package ai.gaiahub.core.domain.services.tracking.events.login

import ai.gaiahub.core.domain.services.tracking.events.EventName

enum class LoginEvents(override val value: String): EventName {
    LOGIN_EVENT("login_event"),
}