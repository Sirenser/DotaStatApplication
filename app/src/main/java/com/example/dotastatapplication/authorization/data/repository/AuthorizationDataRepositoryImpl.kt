package com.example.dotastatapplication.authorization.data.repository

import com.example.dotastatapplication.authorization.data.storage.AuthStorage
import com.example.dotastatapplication.authorization.domain.repository.AuthorizationDataRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthorizationDataRepositoryImpl @Inject constructor(private val storage: AuthStorage) :
    AuthorizationDataRepository {

    override suspend fun saveAccount(accountId: Int) {
        storage.saveAccountId(accountId)
    }

    override suspend fun isOnboarded(): Flow<Boolean> {
        return storage.isOnboarded()
    }


}