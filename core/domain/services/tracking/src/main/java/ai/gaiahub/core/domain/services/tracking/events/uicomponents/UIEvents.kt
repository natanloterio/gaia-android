package ai.gaiahub.core.domain.services.tracking.events.uicomponents

import ai.gaiahub.core.domain.services.tracking.events.EventName

enum class UIEvents(override val value: String): EventName {
    BUTTON_CLICK("button_click")
}