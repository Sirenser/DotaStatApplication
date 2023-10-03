package com.example.dotastatapplication.authorization.utils

import com.example.dotastatapplication.authorization.presenter.models.AccountInfoUI

sealed class ContentViewState {
    object Loading : ContentViewState()
    object FailureConnection : ContentViewState()
    class Success(val data: List<AccountInfoUI>) : ContentViewState()
}
