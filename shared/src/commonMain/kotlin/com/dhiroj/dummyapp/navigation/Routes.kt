package com.dhiroj.dummyapp.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable
@Serializable
sealed interface Routes: NavKey {
    @Serializable
    data object LOGIN : Routes
    @Serializable
    data object HOME : Routes

}