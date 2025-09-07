package ai.gaiahub.tracking

import ai.gaiahub.tracking.events.TrackingEvent
import ai.gaiahub.tracking.models.TrackingResult


interface TrackingExecutor {
    suspend fun executeTracking(event: TrackingEvent): TrackingResult
}