package com.example.dotastatapplication.utils

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey

object PreferencesKeys {
    val keyOnboarded = booleanPreferencesKey("is_onboarded")
    val keyAccountId = intPreferencesKey("account_id")
}