package com.dhiroj.dummyapp.data.model.Quote

import kotlinx.serialization.Serializable

@Serializable
data class Quote(
    val id: Int,
    val quote: String,
    val author: String
)