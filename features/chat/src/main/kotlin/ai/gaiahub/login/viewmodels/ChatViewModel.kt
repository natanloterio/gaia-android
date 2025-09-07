package ai.gaiahub.login.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ai.gaiahub.tracking.contracts.UIComponentsTrackingService
import ai.gaiahub.usecases.LogInUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


sealed class ChatUiState {
    object Idle : ChatUiState()
    object Loading : ChatUiState()
    object Success : ChatUiState()
    data class Error(val message: String) : ChatUiState()
}

// Fake ViewModel to simulate login logic
class ChatViewModel(
    private val uiComponentsTrackingService: UIComponentsTrackingService,
    private val logInUseCase: LogInUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<ChatUiState>(ChatUiState.Idle)
    val uiState = _uiState.asStateFlow()

    fun login(email: String, pass: String) {
        viewModelScope.launch {
            _uiState.value = ChatUiState.Loading
            val attemptToChat = logInUseCase(email = email, password = pass)
            delay(2000) // Simulate network call

            when {
                attemptToChat.isSuccess -> {
                    _uiState.value = ChatUiState.Success
                }

                attemptToChat.isFailure -> {
                    val errorMessage = attemptToChat.exceptionOrNull()?.message ?: "Unknown error"
                    _uiState.value = ChatUiState.Error(errorMessage)
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