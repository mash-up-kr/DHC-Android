package com.dhc.home.model

import com.dhc.dhcandroid.model.HomeViewResponse

data class HomeUiModel(
    val longTermMission: MissionUiModel = MissionUiModel(),
    val todayDailyMissionList: List<MissionUiModel> = emptyList(),
    val todayDailyFortune: TodayDailyFortuneUiModel = TodayDailyFortuneUiModel()
) {
    companion object {
        fun from(homeViewResponse: HomeViewResponse) = HomeUiModel(
            longTermMission = MissionUiModel.from(homeViewResponse.longTermMission),
            todayDailyMissionList = homeViewResponse.todayDailyMissionList.map { MissionUiModel.from(it) },
            todayDailyFortune = TodayDailyFortuneUiModel.from(homeViewResponse.todayDailyFortune)
        )
    }
}
