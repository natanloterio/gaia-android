package ai.gaiahub.login.di

import ai.gaiahub.login.viewmodels.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val loginFeatureModule = module {
    viewModel<LoginViewModel> {
        LoginViewModel(
            uiComponentsTrackingService = get(),
            logInUseCase = get()
        )
    }
}