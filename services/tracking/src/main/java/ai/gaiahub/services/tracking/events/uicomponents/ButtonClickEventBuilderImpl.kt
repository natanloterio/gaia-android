package ai.gaiahub.services.tracking.events.uicomponents

import ai.gaiahub.core.domain.services.tracking.events.uicomponents.ButtonClickEvent
import ai.gaiahub.core.domain.services.tracking.events.uicomponents.ButtonClickEventBuilder

internal class ButtonClickEventBuilderImpl() : ButtonClickEventBuilder() {

    override suspend fun build(): ButtonClickEvent {
        val buttonClickEvent = ButtonClickEvent()

        return buttonClickEvent
    }
}