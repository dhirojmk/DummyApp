package com.dhiroj.dummyapp.presentation.viewModel

import androidx.lifecycle.ViewModel
import com.dhiroj.dummyapp.domain.usecase.AuthUseCase
import androidx.lifecycle.viewModelScope
import com.dhiroj.dummyapp.data.model.login.LoginRequest
import com.dhiroj.dummyapp.data.tokenManager.TokenManager
import com.dhiroj.dummyapp.utils.AuthUiState
import com.dhiroj.dummyapp.utils.NetworkResult
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
    fun clearError(){
        _uiState.update {
            it.copy(
                error = null
            )
        }
    }
    fun login(
        username: String,
        password: String
    ) {
        viewModelScope.launch {
            authUseCase.login(LoginRequest(username, password))
                .collect { result ->
                    when (result) {
                        NetworkResult.Loading -> {
                            _uiState.update {
                                it.copy(isLoading = true, error = null)
                            }
                        }

                        is NetworkResult.Success -> {
                            tokenManager.saveAccessToken(result.data.accessToken)
                            _uiState.update {
                                it.copy(
                                    isLoading = false,
                                    isLoginSuccess = true
                                )
                            }
                        }

                        is NetworkResult.Error -> {
                            _uiState.update {
                                it.copy(
                                    isLoading = false,
                                    error = result.error.message
                                )
                            }
                        }

                        NetworkResult.Empty -> Unit
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