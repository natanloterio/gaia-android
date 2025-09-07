package ai.gaiahub.core.domain.services.tracking.contracts

import ai.gaiahub.core.domain.services.tracking.models.TrackingResult


interface LoginTrackingService {
    suspend fun trackLogin(email: String, isSuccess: Boolean, error: String? = null): TrackingResult

}