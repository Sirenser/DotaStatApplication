package com.example.dotastatapplication.authorization.domain.usecase

import com.example.dotastatapplication.authorization.domain.repository.AuthorizationDataRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class IsOnboardedUseCase
@Inject constructor(private val repository: AuthorizationDataRepository) {

    suspend fun execute(): Flow<Boolean> {
        return repository.isOnboarded()
    }

}