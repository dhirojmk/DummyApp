package com.dhiroj.dummyapp.data.model.login

import kotlinx.serialization.Serializable

@Serializable
data class RefreshResponse(
    val accessToken: String,
    val refreshToken: String
)