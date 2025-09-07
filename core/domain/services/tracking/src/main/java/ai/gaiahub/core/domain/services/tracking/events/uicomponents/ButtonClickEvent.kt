package ai.gaiahub.core.domain.services.tracking.events.uicomponents

import ai.gaiahub.core.domain.services.tracking.events.EventName
import ai.gaiahub.core.domain.services.tracking.events.TrackingEvent
import ai.gaiahub.core.domain.services.tracking.fields.EventField

class ButtonClickEvent : TrackingEvent {
    override val name: EventName = UIEvents.BUTTON_CLICK
    override val timestamp: Long = System.currentTimeMillis()
    override val parameters: Map<EventField, Any?> = emptyMap()
}