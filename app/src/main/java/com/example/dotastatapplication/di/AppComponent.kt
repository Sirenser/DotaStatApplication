package com.example.dotastatapplication.di


import androidx.fragment.app.Fragment
import com.example.dotastatapplication.DotaStatApplication
import com.example.dotastatapplication.MainActivity
import com.example.dotastatapplication.authorization.data.di.AuthBindModule
import com.example.dotastatapplication.authorization.data.di.AuthNetworkModule
import com.example.dotastatapplication.authorization.presenter.ui.AuthorizationFragment
import com.example.dotastatapplication.authorization.presenter.ui.AuthorizationViewModel
import com.example.dotastatapplication.onboarding.data.di.OnboardingBindModule
import com.example.dotastatapplication.onboarding.presenter.ui.OnboardingFragment
import com.example.dotastatapplication.onboarding.presenter.ui.OnboardingViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AuthNetworkModule::class, AuthBindModule::class, OnboardingBindModule::class])
interface AppComponent {
    fun inject(authorizationFragment: AuthorizationFragment)

    fun authViewModel(): AuthorizationViewModel

    fun inject(onboardingFragment: OnboardingFragment)

    fun onboardingViewModel(): OnboardingViewModel

    fun inject(mainActivity: MainActivity)
}

fun Fragment.getAppComponent(): AppComponent =
    (requireContext().applicationContext as DotaStatApplication).appComponent