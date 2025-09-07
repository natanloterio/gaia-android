package ai.gaiahub.tracking.implementations

import ai.gaiahub.tracking.TrackingExecutor
import ai.gaiahub.tracking.contracts.LoginTrackingService
import ai.gaiahub.tracking.events.login.LoginEventBuilderImpl
import ai.gaiahub.tracking.models.TrackingResult

class LoginTrackingServiceImpl(
    private val executor: TrackingExecutor
) : LoginTrackingService {

    override suspend fun trackLogin(
        email: String,
        isSuccess: Boolean,
        error: String?
    ): TrackingResult {
        val builder = LoginEventBuilderImpl()

        val event = builder
            .email(email)
            .success(isSuccess)
            .error(error)
            .build()

        return executor.executeTracking(event = event)
    }
}