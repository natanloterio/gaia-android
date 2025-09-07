package ai.gaiahub.tracking.events

import ai.gaiahub.tracking.fields.EventField

interface TrackingEvent {
    val name: EventName
    val parameters: Map<EventField, Any?>
    val timestamp: Long
}