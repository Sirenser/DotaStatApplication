package com.example.dotastatapplication.authorization.data.model

import com.example.dotastatapplication.authorization.domain.model.AccountInfo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AccountInfoResponse(
    @SerialName("account_id")
    val accountId: Int,
    @SerialName("personaname")
    val personName: String,
    @SerialName("avatarfull")
    val avatarFull: String,
    @SerialName("last_match_time")
    val lastMatchTime: String = "undefined",
    @SerialName("similarity")
    val similarity: Double? = null
)

fun AccountInfoResponse.toDomain(): AccountInfo {
    return AccountInfo(
        accountId = accountId,
        personName = personName,
        avatarFull = avatarFull,
        lastMatchTime = lastMatchTime
    )
}