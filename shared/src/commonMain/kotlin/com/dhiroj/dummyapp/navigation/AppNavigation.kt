package com.dhiroj.dummyapp.navigation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.saveable.rememberSerializable
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.compose.serialization.serializers.SnapshotStateListSerializer
import com.dhiroj.dummyapp.presentation.HomeScreen.HomeScreen
import com.dhiroj.dummyapp.presentation.LoginScreen.LoginScreen
import com.dhiroj.dummyapp.presentation.screen.QuoteScreen

@Composable
fun AppNavigation() {
    val backStack: MutableList<Routes> =
        rememberSerializable(serializer = SnapshotStateListSerializer()) {
            mutableStateListOf(Routes.LOGIN)
        }

    NavDisplay(
        backStack = backStack,
        onBack = {
            if (backStack.size > 1) {
                backStack.removeLastOrNull()
            }
        },
        entryProvider = entryProvider {
            entry<Routes.LOGIN> {
                LoginScreen(
                    onLoginSuccess = {
                        backStack.removeLastOrNull()
                        backStack.add(Routes.HOME)
                    }
                )
            }

            entry<Routes.HOME> {
                HomeScreen()
            }
        }
    )
}