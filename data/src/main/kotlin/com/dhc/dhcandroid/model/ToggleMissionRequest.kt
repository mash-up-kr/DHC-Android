package com.dhc.dhcandroid.model

import kotlinx.serialization.Serializable

@Serializable
data class ToggleMissionRequest(
    val finished: Boolean = false,
    val switch: Boolean = false,
)
