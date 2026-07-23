package com.dhiroj.dummyapp

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.dhiroj.dummyapp.navigation.AppNavigation

@Composable
fun App() {
    MaterialTheme {
        AppNavigation()
    }
}