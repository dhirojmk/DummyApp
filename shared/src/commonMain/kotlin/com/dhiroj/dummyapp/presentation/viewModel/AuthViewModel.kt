package com.dhiroj.dummyapp.presentation.viewModel

import androidx.lifecycle.ViewModel
import com.dhiroj.dummyapp.domain.usecase.AuthUseCase
import androidx.lifecycle.viewModelScope
import com.dhiroj.dummyapp.data.model.login.LoginRequest
import com.dhiroj.dummyapp.data.tokenManager.TokenManager
import com.dhiroj.dummyapp.utils.AuthUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AuthViewModel(
    private val authUseCase: AuthUseCase,
    private val tokenManager: TokenManager

) : ViewModel() {

    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()
    fun login(
        username: String,
        password: String
    ) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isLoading = true,
                    error = null
                )
            }
            try {
                val response = authUseCase.login(
                    LoginRequest(
                        username = username,
                        password = password
                    )
                )
                tokenManager.saveAccessToken(response.accessToken)
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        isLoginSuccess = true,
                        user = null
                    )
                }
            } catch (e: Exception) {

                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = e.message
                    )
                }

            }
        }
    }

    fun getCurrentUser() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isLoading = true,
                    error = null
                )
            }
            try {
                val user = authUseCase.getCurrentUser()
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        user = user
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = e.message
                    )
                }

            }

        }
    }
}