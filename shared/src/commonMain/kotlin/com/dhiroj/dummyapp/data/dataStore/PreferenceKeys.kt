package com.dhiroj.dummyapp.data.dataStore

import androidx.datastore.preferences.core.stringPreferencesKey

object PreferenceKeys {

    val ACCESS_TOKEN =
        stringPreferencesKey("access_token")

    val REFRESH_TOKEN =
        stringPreferencesKey("refresh_token")

    val USER_ID =
        stringPreferencesKey("user_id")
}