package com.example.dotastatapplication.authorization.data.di


import com.example.dotastatapplication.authorization.domain.repository.AccountRepository
import com.example.dotastatapplication.authorization.data.repository.AccountRepositoryImpl
import com.example.dotastatapplication.authorization.data.repository.AuthorizationDataRepositoryImpl
import com.example.dotastatapplication.authorization.data.storage.AuthStorage
import com.example.dotastatapplication.authorization.data.storage.DataStoreAuthStorage
import com.example.dotastatapplication.authorization.domain.repository.AuthorizationDataRepository
import dagger.Binds
import dagger.Module

@Module
interface AuthBindModule {
    @Binds
    fun bindAccountRepository(impl: AccountRepositoryImpl): AccountRepository

    @Binds
    fun bindAuthorizationDataRepository(impl: AuthorizationDataRepositoryImpl): AuthorizationDataRepository

    @Binds
    fun bindAuthStorage(impl: DataStoreAuthStorage): AuthStorage

}