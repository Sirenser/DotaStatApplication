package com.example.dotastatapplication.authorization.presenter.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dotastatapplication.authorization.domain.model.toUI
import com.example.dotastatapplication.authorization.domain.usecase.GetAccountInfoUseCase
import com.example.dotastatapplication.authorization.utils.ContentViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthorizationViewModel @Inject constructor(
    private val useCase: GetAccountInfoUseCase,
) : ViewModel() {

    private val _accountInfoStateFlow: MutableStateFlow<ContentViewState> =
        MutableStateFlow(ContentViewState.Success(emptyList()))

    val accountInfoStateFlow = _accountInfoStateFlow.asStateFlow()

    fun fetchAccount(accountInfo: String) {
        _accountInfoStateFlow.value = ContentViewState.Loading
        viewModelScope.launch {
            useCase.execute(accountInfo).catch {
                _accountInfoStateFlow.value = ContentViewState.FailureConnection
            }.collectLatest { accounts ->
                _accountInfoStateFlow.value = ContentViewState.Success(accounts.map {
                    it.toUI()
                })
            }
        }
    }

    fun saveAccountId(accountId: Int) {
        //TODO Вызов usecase для сохранения id аккаунта в datastore preferenses
    }

}