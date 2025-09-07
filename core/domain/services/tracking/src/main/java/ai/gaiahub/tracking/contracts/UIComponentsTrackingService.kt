package ai.gaiahub.tracking.contracts

import ai.gaiahub.tracking.models.TrackingResult

interface UIComponentsTrackingService {
    suspend fun trackButtonClick(): TrackingResult

}