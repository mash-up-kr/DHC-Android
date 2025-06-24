package com.dhc.dhcandroid.model

import kotlinx.serialization.Serializable

@Serializable
data class MissionSummaryResponse(
    val totalSavedMoney: String = "",
    val weeklySavedMoney: String = "",
    val averageSucceedProbability: Int = 0,
    val calendarDayMissionViews: List<CalendarDayMissionView> = emptyList(),
)

@Serializable
data class CalendarDayMissionView(
    val day: Int = 0,
    val date: String = "",
    val finishedMissionCount: Int = 0,
    val totalMissionCount: Int = 0,
)
