package com.example.dotastatapplication.authorization.presenter.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dotastatapplication.authorization.domain.model.toUI
import com.example.dotastatapplication.authorization.domain.usecase.GetAccountInfoUseCase
import com.example.dotastatapplication.authorization.domain.usecase.IsOnboardedUseCase
import com.example.dotastatapplication.authorization.domain.usecase.SaveAccountInfoUseCase
import com.example.dotastatapplication.authorization.utils.ContentViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthorizationViewModel @Inject constructor(
    private val getAccountUseCase: GetAccountInfoUseCase,
    private val saveAccountInfoUseCase: SaveAccountInfoUseCase,
    private val isOnboardedUseCase: IsOnboardedUseCase
) : ViewModel() {

    private val _accountInfoStateFlow: MutableStateFlow<ContentViewState> =
        MutableStateFlow(ContentViewState.Success(emptyList()))

    val accountInfoStateFlow = _accountInfoStateFlow.asStateFlow()

    private val _destinationStateFlow: MutableStateFlow<NavigationDestination> =
        MutableStateFlow(NavigationDestination.STAY)

    val destinationStateFlow = _destinationStateFlow.asStateFlow()

    fun fetchAccount(accountInfo: String) {
        _accountInfoStateFlow.value = ContentViewState.Loading
        viewModelScope.launch {
            getAccountUseCase.execute(accountInfo).catch {
                _accountInfoStateFlow.value = ContentViewState.FailureConnection
            }.collectLatest { accounts ->
                _accountInfoStateFlow.value = ContentViewState.Success(accounts.map {
                    it.toUI()
                })
            }
        }
    }

    fun saveAccountId(accountId: Int) {
        viewModelScope.launch {
            saveAccountInfoUseCase.execute(accountId)
            if (isOnboarded()) {
                _destinationStateFlow.value = NavigationDestination.TO_OVERVIEW
                println("Destination changed to overview")
            } else {
                _destinationStateFlow.value = NavigationDestination.TO_ONBOARDING
                println("Destination changed to onboarding")
            }
        }
    }

    fun setDefaultDestination() {
        _destinationStateFlow.value = NavigationDestination.STAY
    }

    private fun isOnboarded(): Boolean {
        println("isOnboarded started")
        var result = false
        viewModelScope.launch {
            isOnboardedUseCase.execute().catch {
                result = false
            }.collect { isOnboarded ->
                result = isOnboarded
            }
        }
        return result
    }

}

enum class NavigationDestination {

    STAY, TO_OVERVIEW, TO_ONBOARDING

}
