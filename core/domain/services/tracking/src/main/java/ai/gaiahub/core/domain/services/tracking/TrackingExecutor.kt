package ai.gaiahub.core.domain.services.tracking

import ai.gaiahub.core.domain.services.tracking.events.TrackingEvent
import ai.gaiahub.core.domain.services.tracking.models.TrackingResult


interface TrackingExecutor {
    suspend fun executeTracking(event: TrackingEvent): TrackingResult
}