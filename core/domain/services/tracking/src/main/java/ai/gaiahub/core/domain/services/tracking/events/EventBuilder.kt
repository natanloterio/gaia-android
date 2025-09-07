package ai.gaiahub.core.domain.services.tracking.events

abstract class EventBuilder {
    abstract suspend fun build(): TrackingEvent
}