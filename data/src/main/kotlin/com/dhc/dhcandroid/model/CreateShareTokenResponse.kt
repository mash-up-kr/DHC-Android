package com.dhc.dhcandroid.model

import kotlinx.serialization.Serializable

@Serializable
data class CreateShareTokenResponse(
    val shareCode: String? = null,
    val isNew: Boolean? = null,
)
