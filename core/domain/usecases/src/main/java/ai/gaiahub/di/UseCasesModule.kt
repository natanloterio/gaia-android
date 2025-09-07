package ai.gaiahub.di

import ai.gaiahub.usecases.LogInUseCase
import ai.gaiahub.usecases.LogInUseCaseDefault
import org.koin.dsl.module

val useCasesModule = module{

    factory<LogInUseCase> {
        LogInUseCaseDefault(
            loginTrackingService = get()
        )
    }

}