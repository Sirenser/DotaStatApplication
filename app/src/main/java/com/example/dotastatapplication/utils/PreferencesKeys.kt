package com.example.dotastatapplication.utils

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey

open class PreferencesKeys {
    protected val keyOnboarded = booleanPreferencesKey("is_onboarded")
    protected val keyAccountId = intPreferencesKey("account_id")
}