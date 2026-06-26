package com.dhiroj.dummyapp.data.model.login

import kotlinx.serialization.Serializable

@Serializable
data class RefreshRequest(
    val refreshToken: String,
    val expiresInMins: Int = 30
)