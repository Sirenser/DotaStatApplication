package com.example.dotastatapplication.authorization.presenter.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dotastatapplication.authorization.domain.model.toUI
import com.example.dotastatapplication.authorization.domain.repository.AuthorizationDataRepository
import com.example.dotastatapplication.authorization.domain.usecase.GetAccountInfoUseCase
import com.example.dotastatapplication.authorization.utils.ContentViewState
import com.example.dotastatapplication.utils.NavigationDestination
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthorizationViewModel @Inject constructor(
    private val getAccountUseCase: GetAccountInfoUseCase,
    private val dataRepository: AuthorizationDataRepository
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
            dataRepository.saveAccount(accountId)
            val navDestination = if (isOnboarded()) {
                NavigationDestination.TO_OVERVIEW
            } else {
                NavigationDestination.TO_ONBOARDING
            }
            _destinationStateFlow.value = navDestination
        }
    }

    fun setDefaultDestination() {
        _destinationStateFlow.value = NavigationDestination.STAY
    }

    private fun isOnboarded(): Boolean {
        println("isOnboarded started")
        var result = false
        viewModelScope.launch {
            dataRepository.isOnboarded().catch {
                result = false
            }.collect { isOnboarded ->
                result = isOnboarded
            }
        }
        return result
    }

}
