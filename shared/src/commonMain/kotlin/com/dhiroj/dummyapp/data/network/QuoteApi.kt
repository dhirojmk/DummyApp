package com.dhiroj.dummyapp.data.network

import com.dhiroj.dummyapp.data.model.QuoteResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class QuoteApi(
    private val client: HttpClient
) {
    suspend fun getQuotes(): QuoteResponse {
        return client
            .get("/quotes")
            .body()
    }
}