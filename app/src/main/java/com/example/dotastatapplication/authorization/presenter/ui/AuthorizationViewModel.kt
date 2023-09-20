package com.example.dotastatapplication.authorization.presenter.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AuthorizationViewModel : ViewModel() {

}

@Suppress("UNCHECKED_CAST")
class AuthorizationViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AuthorizationViewModel() as T
    }

}