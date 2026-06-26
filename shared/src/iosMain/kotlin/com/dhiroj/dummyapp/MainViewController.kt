package com.dhiroj.dummyapp

import androidx.compose.ui.window.ComposeUIViewController
import com.dhiroj.dummyapp.di.initKoin

fun MainViewController() = ComposeUIViewController {
    initKoin()
    App()
}