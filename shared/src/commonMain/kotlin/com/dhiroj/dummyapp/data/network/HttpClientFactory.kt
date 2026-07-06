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
            /**
             * onRequest
             * What?
             * A callback executed before each HTTP request is sent.
             * Why?
             * Allows request modification before it reaches the server.
             * Use:
             * Retrieves the access token from TokenManager.
             * Adds the Bearer token to the request header if available
             ***/
        }

        return HttpClient {
            install(authPlugin)
            /**
            What?
            A custom Ktor client plugin.
            Why?
            Automatically intercepts every outgoing request before it is sent.
            Use:
            Adds the Authorization: Bearer <token> header to all authenticated requests.
            Eliminates the need to manually add the token in every API call.
             */
            install(ContentNegotiation) {
                /**
                 * ContentNegotiation
                 * What?
                 * A Ktor plugin that handles request and response serialization.
                 * Why?
                 * Converts Kotlin objects to JSON and JSON responses back to Kotlin objects automatically.
                 * Use:
                 * Removes the need for manual JSON parsing.
                 * */
                json(
                    Json {
                        ignoreUnknownKeys = true
                        /**
                        { ignoreUnknownKeys = true }
                         * What?
                         * JSON deserialization setting.
                         * Why?
                         * Prevents crashes when the server sends additional fields that are not present in the data model.
                         * Use:
                         * Makes API responses backward-compatible with model classes.
                         **/
                        isLenient = true
                        /**
                         * { isLenient = true }
                         * What?
                         * A lenient JSON parsing option.
                         * Why?
                         * Allows parsing of slightly non-standard JSON.
                         * Use:
                         * Improves compatibility with APIs that don't strictly follow the JSON specification.
                         * */
                    }
                )
            }
            install(Logging) {
                /**
                Logging
                What?
                A Ktor plugin for logging network activity.
                Why?
                Helps developers debug API requests and responses.
                Use:
                Prints request URLs, headers, response status, and bodies (depending on configuration).
                 **/
                level = LogLevel.ALL
            }
            defaultRequest {
                url(baseUrl)
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)
            }
            /**
            defaultRequest
            What?
            A block that defines default settings for every request.
            Why?
            Avoids repeating common configurations in each API call.
            Use:
            Sets the base URL.
            Sets default request headers
             **/
            configurePlatform()
        }
    }
}

expect fun HttpClientConfig<*>.configurePlatform()
/**
configurePlatform()
What?
An expect function declared in the shared module and implemented separately for each platform using actual.
Why?
Android and iOS require different HTTP engines and platform-specific networking configurations.
Use:
Configures the appropriate engine (e.g., OkHttp/CIO on Android, Darwin on iOS).
Can also apply platform-specific settings such as timeouts, SSL configuration, or caching.
 **/