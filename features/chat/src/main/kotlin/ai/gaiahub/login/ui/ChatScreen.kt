package ai.gaiahub.login.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ai.gaiahub.login.viewmodels.ChatUiState
import ai.gaiahub.login.viewmodels.ChatViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ChatScreen(viewModel: ChatViewModel = koinViewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    ChatScreenContent(
        uiState = uiState,
        onChat = { email, password -> viewModel.login(email = email, pass = password) },
        onButtonClicked = { viewModel.trackButtonClicked() }
    )
}

@Composable
fun ChatScreenContent(
    uiState: ChatUiState,
    onChat: (String, String) -> Unit,
    onButtonClicked: () -> Unit = {}
) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var isPasswordVisible by rememberSaveable { mutableStateOf(false) }

    var isEmailValid by remember { mutableStateOf(true) }
    var isPasswordValid by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            isError = !isEmailValid,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        if (!isEmailValid) {
            Text("Invalid email format", color = MaterialTheme.colorScheme.error)
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            isError = !isPasswordValid,
            singleLine = true,
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                val image =
                    if (isPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                    Icon(image, "Toggle password visibility")
                }
            },
        )
        if (!isPasswordValid) {
            Text("Password cannot be empty", color = MaterialTheme.colorScheme.error)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                onChat(email, password)
                onButtonClicked()
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = uiState != ChatUiState.Loading
        ) {
            if (uiState == ChatUiState.Loading) {
                CircularProgressIndicator(modifier = Modifier.size(24.dp))
            } else {
                Text("Chat")
            }
        }

        TextButton(onClick = { /* Handle forgot password */ }) {
            Text("Forgot Password?")
        }

        when (val state = uiState) {
            is ChatUiState.Error -> {
                Text(state.message, color = MaterialTheme.colorScheme.error)
            }

            ChatUiState.Success -> {
                // Navigate to next screen
            }

            else -> {}
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChatScreenPreview() {
    MaterialTheme {
        ChatScreenContent(
            uiState = ChatUiState.Idle,
            onChat = { _, _ -> }
        )
    }
}

@Preview(showBackground = true, name = "Loading State")
@Composable
fun ChatScreenLoadingPreview() {
    MaterialTheme {
        ChatScreenContent(
            uiState = ChatUiState.Loading,
            onChat = { _, _ -> }
        )
    }
}

@Preview(showBackground = true, name = "Error State")
@Composable
fun ChatScreenErrorPreview() {
    MaterialTheme {
        ChatScreenContent(
            uiState = ChatUiState.Error("Invalid credentials"),
            onChat = { _, _ -> }
        )
    }
}