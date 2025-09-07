package ai.gaiahub.usecases

import ai.gaiahub.core.domain.services.tracking.contracts.LoginTrackingService
import android.util.Patterns

class LogInUseCaseDefault(
    private val loginTrackingService: LoginTrackingService
) : LogInUseCase() {
    override suspend operator fun invoke(email: String, password: String): Result<Unit> {
        return if (email.isEmailValid() && password.isNotEmpty()) {
            trackLoginEvent(isSuccess = true, email = email)
            Result.success(Unit)
        } else {
            val message = "Invalid email or password"
            trackLoginEvent(isSuccess = false, email = email)
            Result.failure(Exception(message))
        }
    }

    private suspend fun trackLoginEvent(isSuccess: Boolean, email: String, error: String? = null) {
        loginTrackingService.trackLogin(
            email = email,
            isSuccess = isSuccess,
            error = error
        )
    }
}

private fun String.isEmailValid(): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(this).matches()
}