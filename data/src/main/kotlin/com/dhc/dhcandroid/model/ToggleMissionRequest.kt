package com.dhc.dhcandroid.model

data class ToggleMissionRequest(
    val finished: Boolean = false,
    val switch: Boolean = false,
)
