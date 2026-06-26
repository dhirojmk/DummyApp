package com.dhiroj.dummyapp.data.tokenManager

import com.dhiroj.dummyapp.data.dataStore.PreferenceKeys


import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

class TokenManager(
    private val dataStore: DataStore<Preferences>
) {
    val accessToken: Flow<String?>
        get() = dataStore.data.map {
            it[PreferenceKeys.ACCESS_TOKEN]
        }
    suspend fun getAccessToken(): String? {
        return accessToken.firstOrNull()
    }
    suspend fun saveAccessToken(token: String) {
        dataStore.edit {
            it[PreferenceKeys.ACCESS_TOKEN] = token
        }
    }
}