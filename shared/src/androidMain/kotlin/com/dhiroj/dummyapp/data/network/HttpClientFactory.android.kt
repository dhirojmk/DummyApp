package com.dhiroj.dummyapp.data.network

import io.ktor.client.HttpClientConfig
import sp.bvantur.inspektify.ktor.InspektifyKtor

actual fun HttpClientConfig<*>.configurePlatform() {
    install(InspektifyKtor) {
        shortcutEnabled = true
    }
}