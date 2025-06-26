package com.dhc.dhcandroid.model

@Serializable
data class ToggleMissionRequest(
    val finished: Boolean = false,
    val switch: Boolean = false,
)
