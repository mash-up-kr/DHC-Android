package com.dhc.dhcandroid.model

import kotlinx.serialization.Serializable

@Serializable
data class HomeViewResponse(
    val longTermMission: Mission = Mission(),
    val todayDailyMissionList: List<Mission> = emptyList(),
    val todayDailyFortune: DailyFortuneResponse = DailyFortuneResponse(),
    val todayDone: Boolean = false,
    val yesterdayMissionSuccess: Boolean = false,
    val longAbsence: Boolean = false,
    val isFirstAccess: Boolean = false,
    val testBanner: TestBannerResponse = TestBannerResponse(),
)
