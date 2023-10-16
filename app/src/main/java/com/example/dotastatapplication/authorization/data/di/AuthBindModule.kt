package com.example.dotastatapplication.authorization.data.di

import com.example.dotastatapplication.authorization.domain.repository.AccountRepository
import com.example.dotastatapplication.authorization.data.repository.AccountRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface AuthBindModule {
    @Binds
    fun bindAccountRepository(impl: AccountRepositoryImpl): AccountRepository

}