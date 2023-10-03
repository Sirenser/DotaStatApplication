package com.example.dotastatapplication.authorization.data.repository

import com.example.dotastatapplication.authorization.data.model.AccountInfoResponse
import com.example.dotastatapplication.authorization.data.api.AuthDotaApi
import com.example.dotastatapplication.authorization.domain.repository.AccountRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AccountRepositoryImpl @Inject internal constructor(private val api: AuthDotaApi) :
    AccountRepository {
    override suspend fun fetchAccountInfoList(accountInfo: String)
            : Flow<List<AccountInfoResponse>> {
        return flow {
            emit(api.fetchUsers(accountInfo))
        }
    }
}