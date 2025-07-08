package com.dhc.dhcandroid.model

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    val code: Int = 0,
    val message: String = "",
    val details: String? = null,
)
