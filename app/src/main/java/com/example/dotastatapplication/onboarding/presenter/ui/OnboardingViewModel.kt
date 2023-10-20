package com.example.dotastatapplication.onboarding.presenter.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dotastatapplication.onboarding.domain.usecase.SetOnboardedUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class OnboardingViewModel @Inject constructor(private val useCase: SetOnboardedUseCase) :
    ViewModel() {

    suspend fun setOnboarded() {
        viewModelScope.launch {
            useCase.execute()
        }.join()
    }

}