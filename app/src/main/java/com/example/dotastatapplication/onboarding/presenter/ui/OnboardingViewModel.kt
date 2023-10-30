package com.example.dotastatapplication.onboarding.presenter.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dotastatapplication.R
import com.example.dotastatapplication.onboarding.domain.repository.OnboardingRepository
import com.example.dotastatapplication.onboarding.presenter.models.OnboardingModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class OnboardingViewModel @Inject constructor(
    private val onboardingRepository: OnboardingRepository
) : ViewModel() {

    private suspend fun setOnboarded() {
        onboardingRepository.setOnboarded()
    }

    fun onSkipOrLastItemClicked() = viewModelScope.launch {
        setOnboarded()
    }

    fun setupOnboardingModels(): List<OnboardingModel> = listOf(
            OnboardingModel(
                onboardingImage = R.drawable.icon_dota,
                title = R.string.onboaring_matches_title,
                description = R.string.onboaring_matches_description
            ),
            OnboardingModel(
                onboardingImage = R.drawable.icon_dota,
                title = R.string.onboaring_heroes_title,
                description = R.string.onboaring_heroes_description
            ),
            OnboardingModel(
                onboardingImage = R.drawable.icon_dota,
                title = R.string.onboaring_statistic_title,
                description = R.string.onboaring_statistic_description
            ),
            OnboardingModel(
                onboardingImage = R.drawable.icon_dota,
                title = R.string.onboaring_profile_title,
                description = R.string.onboaring_profile_description
            )
        )

}