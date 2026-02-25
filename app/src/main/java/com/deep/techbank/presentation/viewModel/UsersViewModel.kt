package com.deep.techbank.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.deep.techbank.domain.model.Users
import com.deep.techbank.domain.usecase.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


// 1. Define the UI State
data class UserState(
    val usersList: List<Users> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

// 2. Define the User Intents/Actions
sealed interface UserIntent {
    object RetrieveUsers : UserIntent
}

// 3. Create the ViewModel
@HiltViewModel
class UsersViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(UserState())
    val state: StateFlow<UserState> = _state.asStateFlow()

    init {
        // Start observing the user list as soon as the ViewModel is created
        viewModelScope.launch {
            getUsersUseCase.users
                .onStart { _state.update { it.copy(isLoading = true) } }
                .catch { throwable ->
                    _state.update { it.copy(isLoading = false, error = throwable.message) }
                }
                .collect { userList ->
                    _state.update { it.copy(isLoading = false, usersList = userList) }
                }
        }
    }

    fun onIntent(intent: UserIntent) {
        when (intent) {
            is UserIntent.RetrieveUsers -> {
                viewModelScope.launch {
                    getUsersUseCase()
                }
            }
        }
    }
}
