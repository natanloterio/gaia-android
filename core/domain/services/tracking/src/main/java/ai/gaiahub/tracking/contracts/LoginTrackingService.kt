package ai.gaiahub.tracking.contracts

import ai.gaiahub.tracking.models.TrackingResult


interface LoginTrackingService {
    suspend fun trackLogin(email: String, isSuccess: Boolean, error: String? = null): TrackingResult

}