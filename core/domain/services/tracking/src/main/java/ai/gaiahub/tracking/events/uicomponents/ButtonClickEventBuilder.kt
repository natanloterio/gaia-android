package ai.gaiahub.tracking.events.uicomponents

import ai.gaiahub.tracking.events.EventBuilder

abstract class ButtonClickEventBuilder() : EventBuilder() {

    abstract override suspend fun build(): ButtonClickEvent
}