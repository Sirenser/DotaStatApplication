package com.example.dotastatapplication.authorization.data.api

import com.example.dotastatapplication.authorization.data.model.AccountInfoResponse
import retrofit2.http.GET
import retrofit2.http.Query

internal interface AuthDotaApi {

    @GET("search")
    suspend fun fetchUsers(@Query("q") accountId: String): List<AccountInfoResponse>

}