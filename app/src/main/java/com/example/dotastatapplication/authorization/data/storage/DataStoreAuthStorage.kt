package com.example.dotastatapplication.authorization.data.storage

import android.content.Context
import androidx.datastore.preferences.core.edit
import com.example.dotastatapplication.onboarding.data.storage.dataStore
import com.example.dotastatapplication.utils.PreferencesKeys
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStoreAuthStorage @Inject constructor(private val context: Context) : AuthStorage {

    override suspend fun isOnboarded(): Flow<Boolean> {
        return context.dataStore.data.map { preferences ->
            preferences[PreferencesKeys.keyOnboarded] ?: false
        }
    }

    override suspend fun saveAccountId(accountId: Int) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.keyAccountId] = accountId
        }
    }
}