package com.dhc.dhcandroid.model

import kotlinx.serialization.Serializable

@Serializable
data class HomeViewResponse(
    val longTermMission: Mission = Mission(),
    val todayDailyMissionList: List<Mission> = emptyList(),
    val todayDailyFortune: DailyFortune = DailyFortune(),
)
