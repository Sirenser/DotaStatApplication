package com.example.dotastatapplication.authorization.domain.usecase

import com.example.dotastatapplication.authorization.data.model.toDomain
import com.example.dotastatapplication.authorization.domain.repository.AccountRepository
import com.example.dotastatapplication.authorization.domain.model.AccountInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAccountInfoUseCase @Inject constructor(
    private val repository: AccountRepository
) {
    fun execute(accountId: String): Flow<List<AccountInfo>> {
        return flow {
            repository.fetchAccountInfoList(accountId).collect { accountInfoResponses ->
                emit(accountInfoResponses.map { accountInfoResponse ->
                    accountInfoResponse.toDomain()
                })
            }
        }
    }
}