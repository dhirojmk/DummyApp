package com.dhiroj.dummyapp.navigation
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dhiroj.dummyapp.presentation.LoginScreen.LoginScreen
import com.dhiroj.dummyapp.presentation.screen.QuoteScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Routes.LOGIN
    ) {
        composable<Routes.LOGIN> {
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
        composable<Routes.HOME> {
            QuoteScreen()
        }
    }
}