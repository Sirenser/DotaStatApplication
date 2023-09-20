package com.example.dotastatapplication.di


import androidx.fragment.app.Fragment
import com.example.dotastatapplication.DotaStatApplication
import com.example.dotastatapplication.MainActivity
import com.example.dotastatapplication.authorization.data.di.AuthNetworkModule
import com.example.dotastatapplication.authorization.presenter.ui.AuthorizationFragment
import dagger.Component

@Component(modules = [AuthNetworkModule::class])
interface AppComponent {
    fun inject(authorizationFragment: AuthorizationFragment)
    fun inject(mainActivity: MainActivity)
}

fun Fragment.getAppComponent(): AppComponent =
    (requireContext().applicationContext as DotaStatApplication).appComponent