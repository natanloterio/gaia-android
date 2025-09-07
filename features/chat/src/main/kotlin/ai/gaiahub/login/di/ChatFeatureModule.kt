package ai.gaiahub.login.di

import ai.gaiahub.login.viewmodels.ChatViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val chatFeatureModule = module {
    viewModel<ChatViewModel> {
        ChatViewModel(
            uiComponentsTrackingService = get(),
            logInUseCase = get()
        )
    }
}