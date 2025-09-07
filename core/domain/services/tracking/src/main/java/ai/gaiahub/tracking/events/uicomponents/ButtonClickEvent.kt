package ai.gaiahub.tracking.events.uicomponents

import ai.gaiahub.tracking.events.EventName
import ai.gaiahub.tracking.events.TrackingEvent
import ai.gaiahub.tracking.fields.EventField

class ButtonClickEvent : TrackingEvent {
    override val name: EventName = UIEvents.BUTTON_CLICK
    override val timestamp: Long = System.currentTimeMillis()
    override val parameters: Map<EventField, Any?> = emptyMap()
}