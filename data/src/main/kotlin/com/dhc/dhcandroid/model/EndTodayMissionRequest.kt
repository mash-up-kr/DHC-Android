package com.dhc.dhcandroid.model

import kotlinx.serialization.Serializable

@Serializable
data class EndTodayMissionRequest(
    val date: String = "",
)
