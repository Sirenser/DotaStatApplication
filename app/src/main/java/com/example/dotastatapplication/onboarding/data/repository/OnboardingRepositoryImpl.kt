package com.example.dotastatapplication.onboarding.data.repository


import com.example.dotastatapplication.onboarding.data.storage.OnboardedStorage
import com.example.dotastatapplication.onboarding.domain.repository.OnboardingRepository
import javax.inject.Inject


class OnboardingRepositoryImpl @Inject constructor(private val storage: OnboardedStorage) :
    OnboardingRepository {
    override suspend fun setOnboarded() {
        storage.setIsOnboarded()
    }
}