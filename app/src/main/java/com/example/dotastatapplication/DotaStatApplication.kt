package com.example.dotastatapplication

import android.app.Application
import com.example.dotastatapplication.di.AppComponent
import com.example.dotastatapplication.di.DaggerAppComponent

class DotaStatApplication : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder().build()
    }

}