package com.dhc.home.model

import com.dhc.common.DateUtil
import com.dhc.dhcandroid.model.HomeViewResponse

data class HomeUiModel(
    val longTermMission: MissionUiModel = MissionUiModel(),
    val todayDailyMissionList: List<MissionUiModel> = emptyList(),
    val todayDailyFortune: TodayDailyFortuneUiModel = TodayDailyFortuneUiModel(),
    val todayDone: Boolean = false,
    val remainingMissionTimer: Long = 0L
) {
    companion object {
        fun from(homeViewResponse: HomeViewResponse) = HomeUiModel(
            longTermMission = MissionUiModel.from(homeViewResponse.longTermMission),
            todayDailyMissionList = homeViewResponse.todayDailyMissionList.map { MissionUiModel.from(it) },
            todayDailyFortune = TodayDailyFortuneUiModel.from(homeViewResponse.todayDailyFortune),
            todayDone = homeViewResponse.todayDone,
            remainingMissionTimer = DateUtil.getTimeUntilMidnight()
        )
    }
}
