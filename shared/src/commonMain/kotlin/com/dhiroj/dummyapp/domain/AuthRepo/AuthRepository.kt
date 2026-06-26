package com.dhiroj.dummyapp.domain.AuthRepo

import com.dhiroj.dummyapp.data.model.login.LoginRequest
import com.dhiroj.dummyapp.data.model.login.LoginResponse
import com.dhiroj.dummyapp.data.model.login.RefreshRequest
import com.dhiroj.dummyapp.data.model.login.RefreshResponse
import com.dhiroj.dummyapp.data.model.login.UserResponse

interface AuthRepository {

    suspend fun login(
        request: LoginRequest
    ): LoginResponse

    suspend fun getCurrentUser(): UserResponse

    suspend fun refreshToken(
        request: RefreshRequest
    ): RefreshResponse
}