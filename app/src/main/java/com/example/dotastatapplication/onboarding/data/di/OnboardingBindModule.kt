package com.example.dotastatapplication.onboarding.data.di

import com.example.dotastatapplication.onboarding.data.repository.OnboardingRepositoryImpl
import com.example.dotastatapplication.onboarding.data.storage.DataStoreOnboardingStorage
import com.example.dotastatapplication.onboarding.data.storage.OnboardedStorage
import com.example.dotastatapplication.onboarding.domain.repository.OnboardingRepository
import dagger.Binds
import dagger.Module

@Module
interface OnboardingBindModule {

    @Binds
    fun bindOnboardingRepository(impl: OnboardingRepositoryImpl): OnboardingRepository

    @Binds
    fun bindOnboardingStorage(impl: DataStoreOnboardingStorage): OnboardedStorage

}