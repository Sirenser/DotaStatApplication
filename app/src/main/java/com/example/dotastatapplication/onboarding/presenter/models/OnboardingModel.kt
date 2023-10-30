package com.example.dotastatapplication.onboarding.presenter.models

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class OnboardingModel(
    @DrawableRes val onboardingImage: Int,
    @StringRes val title: Int,
    @StringRes val description: Int
)