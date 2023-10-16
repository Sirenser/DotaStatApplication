package com.example.dotastatapplication.authorization.domain.model

import com.example.dotastatapplication.authorization.presenter.models.AccountInfoUI

data class AccountInfo(
    val accountId: Int,
    val avatarFull: String,
    val lastMatchTime: String,
    val personName: String,
)

fun AccountInfo.toUI(): AccountInfoUI {
    return AccountInfoUI(
        personName = personName,
        avatarFull = avatarFull
    )
}

