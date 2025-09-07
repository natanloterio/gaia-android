package ai.gaiahub.core.domain.services.tracking.contracts

import ai.gaiahub.core.domain.services.tracking.models.TrackingResult

interface UIComponentsTrackingService {
    suspend fun trackButtonClick(): TrackingResult

}