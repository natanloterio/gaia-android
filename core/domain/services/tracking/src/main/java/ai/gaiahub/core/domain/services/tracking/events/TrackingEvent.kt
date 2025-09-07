package ai.gaiahub.core.domain.services.tracking.events

import ai.gaiahub.core.domain.services.tracking.fields.EventField

interface TrackingEvent {
    val name: EventName
    val parameters: Map<EventField, Any?>
    val timestamp: Long
}