package com.example.dotastatapplication.onboarding.domain.usecase

import com.example.dotastatapplication.onboarding.domain.repository.OnboardingRepository
import javax.inject.Inject

class SetOnboardedUseCase @Inject constructor(
    private val onboardingRepository: OnboardingRepository
) {
    suspend fun execute() {
        onboardingRepository.setOnboarded()
    }
}