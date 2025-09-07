package ai.gaiahub.tracking.events

abstract class EventBuilder {
    abstract suspend fun build(): TrackingEvent
}