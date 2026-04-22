package com.sabrina.recipeflow.ui.screens.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sabrina.data.local.UserPreferencesRepository
import com.sabrina.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository,
    private val userPreferences: UserPreferencesRepository
) : ViewModel() {

    private val _state = MutableStateFlow(AuthState())
    val state = _state.asStateFlow()

    fun onEvent(email: String, pass: String, isLogin: Boolean) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            val result = if (isLogin) {
                repository.login(email,pass)
            } else{
                repository.signUp(email,pass)
            }

            result.onSuccess {
                _state.update { it.copy(isLoading = false, isSuccess = true) }
            }.onFailure { e->
                _state.update { it.copy(isLoading = false, error = e.message) }
            }
        }
    }

    fun onGetStarted(onSuccess: () -> Unit) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }

            val result = repository.signInAnonymously()

            result.onSuccess {
                userPreferences.setNotFirstTime()
                _state.update { it.copy(isLoading = false, isSuccess = true) }
                onSuccess()
            }.onFailure { e ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        error = e.message ?: "Could not connect to cloud."
                    )
                }
            }
        }
    }
}