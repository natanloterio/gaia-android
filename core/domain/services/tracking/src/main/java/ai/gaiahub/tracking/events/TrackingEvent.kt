package ai.gaiahub.tracking.events

import ai.gaiahub.tracking.fields.EventField

interface TrackingEvent {
    abstract val name: EventName
    abstract val parameters: Map<EventField, Any?>
    abstract val timestamp: Long
}