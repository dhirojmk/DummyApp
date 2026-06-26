package com.dhiroj.dummyapp

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dhiroj.dummyapp.navigation.Routes
import com.dhiroj.dummyapp.presentation.LoginScreen.LoginScreen
import com.dhiroj.dummyapp.presentation.screen.QuoteScreen

@Composable
fun App() {

    MaterialTheme {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = Routes.LOGIN
        ) {

            composable(Routes.LOGIN) {
                LoginScreen(
                    onLoginSuccess = {
                        navController.navigate(Routes.HOME) {
                            popUpTo(Routes.LOGIN) {
                                inclusive = true
                            }
                        }
                    }
                )
            }
            composable(Routes.HOME) {
                QuoteScreen()
            }
        }
    }
}