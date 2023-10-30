package com.example.dotastatapplication.authorization.data.storage

import kotlinx.coroutines.flow.Flow

interface AuthStorage {

    suspend fun isOnboarded(): Flow<Boolean>

    suspend fun saveAccountId(accountId: Int)

}