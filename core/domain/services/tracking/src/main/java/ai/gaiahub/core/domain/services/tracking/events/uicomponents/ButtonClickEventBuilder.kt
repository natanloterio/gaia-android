package ai.gaiahub.core.domain.services.tracking.events.uicomponents

import ai.gaiahub.core.domain.services.tracking.events.EventBuilder

abstract class ButtonClickEventBuilder() : EventBuilder() {

    abstract override suspend fun build(): ButtonClickEvent
}