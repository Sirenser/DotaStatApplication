package com.example.dotastatapplication.onboarding.data.storage

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.dotastatapplication.utils.PreferencesKeys
import javax.inject.Inject


private const val SHARED_PREFS_NAME = "shared_prefs_name"

class DataStoreOnboardingStorage @Inject constructor(private val context: Context) :
    OnboardedStorage {
    override suspend fun setIsOnboarded() {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.keyOnboarded] = true
            println("IsOnboarded in storage sets = ${preferences[PreferencesKeys.keyOnboarded]}")
        }
    }
}

val Context.dataStore by preferencesDataStore(name = SHARED_PREFS_NAME)