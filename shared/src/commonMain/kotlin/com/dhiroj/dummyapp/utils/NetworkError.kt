package com.dhiroj.dummyapp.utils

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkError(

    @SerialName("status")
    val statusCode: Int? = null,

    @SerialName("title")
    val title: String? = null,

    @SerialName("detail")
    val message: String? = null,

    @SerialName("error")
    val error: String? = null,

    @SerialName("path")
    val path: String? = null
)