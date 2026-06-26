package com.dhiroj.dummyapp.utils

import com.dhiroj.dummyapp.data.model.login.UserResponse

data class AuthUiState(
    val isLoading: Boolean = false,
    val user: UserResponse? = null,
    val error: String? = null,
    val isLoginSuccess: Boolean = false
)