package ai.gaiahub.services.tracking.implementations

import ai.gaiahub.core.domain.services.tracking.TrackingExecutor
import ai.gaiahub.core.domain.services.tracking.contracts.LoginTrackingService
import ai.gaiahub.services.tracking.events.login.LoginEventBuilderImpl
import ai.gaiahub.core.domain.services.tracking.models.TrackingResult

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