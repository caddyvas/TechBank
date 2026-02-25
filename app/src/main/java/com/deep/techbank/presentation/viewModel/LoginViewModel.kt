package com.deep.techbank.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deep.techbank.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

// 1. Define the UI State
data class LoginState(
    val userType: String = "Admin",
    val username: String = "",
    val accountNumber: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val loginSuccess: Boolean = false
)

// 2. Define the User Intents/Actions
sealed interface LoginIntent {
    data class UserTypeChange(val type: String) : LoginIntent
    data class UsernameChanged(val username: String) : LoginIntent
    data class AccountNumberChanged(val accountNumber: String) : LoginIntent
    data class PasswordChanged(val password: String) : LoginIntent
    object SubmitLogin : LoginIntent
    object NavigationHandled : LoginIntent // <-- NEW INTENT
}

// 3. Create the ViewModel
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> = _state.asStateFlow()

    fun onIntent(intent: LoginIntent) {
        when (intent) {
            is LoginIntent.UserTypeChange -> {
                _state.update { it.copy(userType = intent.type) }
            }
            is LoginIntent.UsernameChanged -> {
                _state.update { it.copy(username = intent.username) }
            }
            is LoginIntent.AccountNumberChanged -> {
                _state.update { it.copy(accountNumber = intent.accountNumber) }
            }
            is LoginIntent.PasswordChanged -> {
                _state.update { it.copy(password = intent.password) }
            }
            is LoginIntent.SubmitLogin -> {
                handleLogin()
            }
            // NEW: Handle the event consumption
            is LoginIntent.NavigationHandled -> {
                _state.update { it.copy(loginSuccess = false) }
            }
        }
    }

    private fun handleLogin() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            val currentState = _state.value
            val result = if (currentState.userType == "Admin") {
                loginUseCase(currentState.username, currentState.password)
            } else {
                // TODO: Create a separate use case for customer login
                loginUseCase(currentState.accountNumber, currentState.password)
            }

            result.onSuccess {
                _state.update { it.copy(isLoading = false, loginSuccess = true) }
            }.onFailure {
                _state.update { it.copy(isLoading = false, error = "") } // it.message
            }
        }
    }
}
