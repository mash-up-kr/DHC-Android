package com.dhc.dhcandroid.model

import kotlinx.serialization.Serializable

@Serializable
data class EndTodayMissionResponse(
    val todaySavedMoney: String = "",
)
