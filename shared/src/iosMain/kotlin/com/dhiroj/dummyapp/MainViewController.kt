package com.dhiroj.dummyapp

import androidx.compose.ui.window.ComposeUIViewController
import com.dhiroj.dummyapp.di.initKoin

//fun MainViewController() = ComposeUIViewController {
//    initKoin() //Koin Initialize here
//    App()
//}
fun MainViewController() = ComposeUIViewController (
    configure = {
        initKoin()
    }
){ App() }