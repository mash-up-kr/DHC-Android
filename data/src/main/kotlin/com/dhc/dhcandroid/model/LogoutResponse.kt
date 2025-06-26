package com.dhc.dhcandroid.model

import kotlinx.serialization.Serializable

@Serializable
data class LogoutResponse(
    val message: String = "",
)
