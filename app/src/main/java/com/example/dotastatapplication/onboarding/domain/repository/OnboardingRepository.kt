package com.example.dotastatapplication.onboarding.domain.repository

interface OnboardingRepository {
    suspend fun setOnboarded()
}