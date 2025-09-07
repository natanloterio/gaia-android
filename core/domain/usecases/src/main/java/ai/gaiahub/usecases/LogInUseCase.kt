package ai.gaiahub.usecases

abstract class LogInUseCase {

    abstract suspend operator fun invoke(email: String, password: String): Result<Unit>
}