package com.dhiroj.dummyapp.domain.repositoryImpl

import com.dhiroj.dummyapp.data.model.login.LoginRequest
import com.dhiroj.dummyapp.data.model.login.LoginResponse
import com.dhiroj.dummyapp.data.model.login.RefreshRequest
import com.dhiroj.dummyapp.data.model.login.RefreshResponse
import com.dhiroj.dummyapp.data.model.login.UserResponse
import com.dhiroj.dummyapp.data.network.AuthApi
import com.dhiroj.dummyapp.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val authApi: AuthApi
) : AuthRepository {
    override suspend fun login(
        request: LoginRequest
    ): LoginResponse {
        return authApi.login(request)
    }

    override suspend fun getCurrentUser(): UserResponse {
        return authApi.getCurrentUser()
    }

    override suspend fun refreshToken(
        request: RefreshRequest
    ): RefreshResponse {
        return authApi.refreshToken(request)
    }

}