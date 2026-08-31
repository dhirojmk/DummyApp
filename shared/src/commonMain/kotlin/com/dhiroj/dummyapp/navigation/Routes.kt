package com.dhiroj.dummyapp.navigation

import kotlinx.serialization.Serializable
@Serializable
sealed class Routes {
    @Serializable
    data object LOGIN : Routes()
    @Serializable
    data object HOME : Routes()

}