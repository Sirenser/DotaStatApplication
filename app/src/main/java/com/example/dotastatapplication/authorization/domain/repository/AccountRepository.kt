package com.example.dotastatapplication.authorization.domain.repository

import com.example.dotastatapplication.authorization.data.model.AccountInfoResponse
import kotlinx.coroutines.flow.Flow


interface AccountRepository {
    suspend fun fetchAccountInfoList(accountInfo: String): Flow<List<AccountInfoResponse>>
}