package com.dhc.dhcandroid.model

import kotlinx.serialization.Serializable

@Serializable
data class ToggleMissionRequest(
    val finished: Boolean? = null,
    val switch: Boolean? = null,
)
