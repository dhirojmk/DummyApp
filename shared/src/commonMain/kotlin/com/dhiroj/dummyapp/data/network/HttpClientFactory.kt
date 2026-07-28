package com.dhiroj.dummyapp.data.network

import com.dhiroj.dummyapp.data.tokenManager.TokenManager
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.api.createClientPlugin
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.accept
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object HttpClientFactory {
    fun create(
        baseUrl: String,
        tokenManager: TokenManager
    ): HttpClient {
        val authPlugin = createClientPlugin("AuthPlugin") {
            onRequest { request, _ ->
                val token = tokenManager.getAccessToken()
                if (!token.isNullOrBlank()) {
                    request.headers.append(
                        HttpHeaders.Authorization,
                        "Bearer $token"
                    )
                }
            }
        }

        return HttpClient {
            install(authPlugin)
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        isLenient = true
                    }
                )
            }
            install(Logging) {
                level = LogLevel.ALL
            }
            defaultRequest {
                url(baseUrl)
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)
            }
            configurePlatform()
        }
    }
}

expect fun HttpClientConfig<*>.configurePlatform()