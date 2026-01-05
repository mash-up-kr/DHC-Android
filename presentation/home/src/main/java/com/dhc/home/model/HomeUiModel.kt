package com.dhc.home.model

import com.dhc.common.DateUtil
import com.dhc.dhcandroid.model.HomeViewResponse

data class HomeUiModel(
    val longTermMission: MissionUiModel = MissionUiModel(),
    val todayDailyMissionList: List<MissionUiModel> = emptyList(),
    val todayDailyFortune: TodayDailyFortuneUiModel = TodayDailyFortuneUiModel(),
    val todayDone: Boolean = false,
    val remainingMissionTimer: Long = 0L,
    val rewardEventTitle: String = "오늘의 미션",
    val rewardEventSubtitle: String = "단 3개만 도전해 보세요",
    val rewardCompletedCount: Int = 0,
    val rewardTotalCount: Int = 3
) {
    companion object {
        fun from(homeViewResponse: HomeViewResponse) = HomeUiModel(
            longTermMission = MissionUiModel.from(homeViewResponse.longTermMission),
            todayDailyMissionList = homeViewResponse.todayDailyMissionList.map { MissionUiModel.from(it) },
            todayDailyFortune = TodayDailyFortuneUiModel.from(homeViewResponse.todayDailyFortune),
            todayDone = homeViewResponse.todayDone,
            remainingMissionTimer = DateUtil.getTimeUntilMidnight(),
            rewardEventTitle = "오늘의 미션",
            rewardEventSubtitle = "단 3개만 도전해 보세요",
            rewardCompletedCount =  0,
            rewardTotalCount =  3     // TODO - 서버 response 확인 후 변경 예정
        )
    }
}
