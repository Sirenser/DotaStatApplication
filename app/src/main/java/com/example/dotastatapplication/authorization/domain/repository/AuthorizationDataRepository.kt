package com.example.dotastatapplication.authorization.domain.repository

import kotlinx.coroutines.flow.Flow

interface AuthorizationDataRepository {

    suspend fun saveAccount(accountId: Int)

    suspend fun isOnboarded(): Flow<Boolean>

}