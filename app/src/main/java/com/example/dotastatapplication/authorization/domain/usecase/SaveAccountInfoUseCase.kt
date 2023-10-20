package com.example.dotastatapplication.authorization.domain.usecase

import com.example.dotastatapplication.authorization.domain.repository.AuthorizationDataRepository
import javax.inject.Inject


class SaveAccountInfoUseCase
@Inject constructor(private val repository: AuthorizationDataRepository) {

    suspend fun execute(accountId: Int) {
        repository.saveAccount(accountId)
    }


}