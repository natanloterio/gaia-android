package ai.gaiahub.login.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ai.gaiahub.tracking.contracts.UIComponentsTrackingService
import ai.gaiahub.usecases.LogInUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


sealed class LoginUiState {
    object Idle : LoginUiState()
    object Loading : LoginUiState()
    object Success : LoginUiState()
    data class Error(val message: String) : LoginUiState()
}

// Fake ViewModel to simulate login logic
class LoginViewModel(
    private val uiComponentsTrackingService: UIComponentsTrackingService,
    private val logInUseCase: LogInUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<LoginUiState>(LoginUiState.Idle)
    val uiState = _uiState.asStateFlow()

    fun login(email: String, pass: String) {
        viewModelScope.launch {
            _uiState.value = LoginUiState.Loading
            val attemptToLogin = logInUseCase(email = email, password = pass)
            delay(2000) // Simulate network call

            when {
                attemptToLogin.isSuccess -> {
                    _uiState.value = LoginUiState.Success
                }

                attemptToLogin.isFailure -> {
                    val errorMessage = attemptToLogin.exceptionOrNull()?.message ?: "Unknown error"
                    _uiState.value = LoginUiState.Error(errorMessage)
                }
            }
        }
    }

    fun trackButtonClicked() {
        viewModelScope.launch {
            uiComponentsTrackingService.trackButtonClick()
        }
    }
}