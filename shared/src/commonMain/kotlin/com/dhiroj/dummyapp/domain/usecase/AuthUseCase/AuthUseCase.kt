package com.dhiroj.dummyapp.domain.usecase.AuthUseCase

import com.dhiroj.dummyapp.data.model.login.LoginRequest
import com.dhiroj.dummyapp.data.model.login.LoginResponse
import com.dhiroj.dummyapp.data.model.login.RefreshRequest
import com.dhiroj.dummyapp.data.model.login.RefreshResponse
import com.dhiroj.dummyapp.data.model.login.UserResponse
import com.dhiroj.dummyapp.domain.AuthRepo.AuthRepository

class AuthUseCase(
    private val repository: AuthRepository
) {

    suspend fun login(
        request: LoginRequest
    ): LoginResponse {
        return repository.login(request)
    }

    suspend fun getCurrentUser(): UserResponse {
        return repository.getCurrentUser()
    }

    suspend fun refreshToken(
        request: RefreshRequest
    ): RefreshResponse {
        return repository.refreshToken(request)
    }
}