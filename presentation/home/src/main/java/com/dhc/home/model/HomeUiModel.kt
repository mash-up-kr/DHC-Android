package com.dhc.home.model

import com.dhc.common.DateUtil
import com.dhc.dhcandroid.model.HomeViewResponse

data class HomeUiModel(
    val longTermMission: MissionUiModel = MissionUiModel(),
    val todayDailyMissionList: List<MissionUiModel> = emptyList(),
    val todayDailyFortune: TodayDailyFortuneUiModel = TodayDailyFortuneUiModel(),
    val todayDone: Boolean = false,
    val yesterdayMissionSuccess: Boolean = false,
    val longAbsence: Boolean = false,
    val isFirstAccess: Boolean = false,
    val remainingMissionTimer: Long = 0L,
    val rewardEvent: RewardEvent = RewardEvent()
) {
    companion object {
        fun from(homeViewResponse: HomeViewResponse) = HomeUiModel(
            longTermMission = MissionUiModel.from(homeViewResponse.longTermMission),
            todayDailyMissionList = homeViewResponse.todayDailyMissionList.map { MissionUiModel.from(it) },
            todayDailyFortune = TodayDailyFortuneUiModel.from(homeViewResponse.todayDailyFortune),
            todayDone = homeViewResponse.todayDone,
            yesterdayMissionSuccess = homeViewResponse.yesterdayMissionSuccess,
            longAbsence = homeViewResponse.longAbsence,
            isFirstAccess = homeViewResponse.isFirstAccess,
            remainingMissionTimer = DateUtil.getTimeUntilMidnight(),
            rewardEvent = makeRewardEventContent(homeViewResponse)
        )


        fun makeRewardEventContent(homeViewResponse: HomeViewResponse): RewardEvent {
            val finishedCount =
                listOfNotNull(homeViewResponse.longTermMission)
                    .count { it.finished } +
                        homeViewResponse.todayDailyMissionList.count { it.finished }

            return when {
                // 첫 접속 (가입 첫날): 어제 미션 기회 없음 → 이벤트 없음
                homeViewResponse.isFirstAccess -> {
                    val (title, subtitle) = when (finishedCount) {
                        0 -> "오늘의 미션" to "단 3개만 도전해 보세요"
                        1 -> "오늘의 미션" to "벌써 한개나 성공했네요!"
                        2 -> "오늘의 미션" to "리워드까지 한 걸음 남았어요!"
                        else -> "오늘의 미션" to "단 3개만 도전해 보세요"
                    }
                    RewardEvent(
                        rewardEventTitle = title,
                        rewardEventSubtitle = subtitle,
                        rewardCompletedCount = finishedCount,
                        rewardTotalCount = 3
                    )
                }
                // 2일 이상 미접속
                !homeViewResponse.todayDone && homeViewResponse.longAbsence -> {
                    RewardEvent(
                        rewardEventTitle = "웰컴백 이벤트",
                        rewardEventSubtitle = "오늘은 리워드 보상이 4배에요!",
                        rewardCompletedCount = finishedCount,
                        rewardTotalCount = 3
                    )
                }
                // 어제 미션 실패로 인한 2배 이벤트
                !homeViewResponse.todayDone && !homeViewResponse.yesterdayMissionSuccess -> {
                    RewardEvent(
                        rewardEventTitle = "리워드 2배 이벤트",
                        rewardEventSubtitle = "오늘은 리워드 보상이 2배에요!",
                        rewardCompletedCount = finishedCount,
                        rewardTotalCount = 3
                    )
                }
                // 오늘 미션 완료했지만 실패
                homeViewResponse.todayDone && finishedCount != 3 -> {
                    RewardEvent(
                        rewardEventTitle = "아쉽게 실패했어요",
                        rewardEventSubtitle = "하지만 내일 미션을 성공하면 리워드가 두배에요!",
                        rewardCompletedCount = finishedCount,
                        rewardTotalCount = 3
                    )
                }
                // 오늘 미션 진행 중
                else -> {
                    val (title, subtitle) = when (finishedCount) {
                        0 -> "오늘의 미션" to "단 3개만 도전해 보세요"
                        1 -> "오늘의 미션" to "벌써 한개나 성공했네요!"
                        2 -> "오늘의 미션" to "리워드까지 한 걸음 남았어요!"
                        else -> "오늘의 미션" to "단 3개만 도전해 보세요"
                    }
                    RewardEvent(
                        rewardEventTitle = title,
                        rewardEventSubtitle = subtitle,
                        rewardCompletedCount = finishedCount,
                        rewardTotalCount = 3
                    )
                }
            }
        }
    }
}

data class RewardEvent(
    val rewardEventTitle: String = "오늘의 미션",
    val rewardEventSubtitle: String = "단 3개만 도전해 보세요",
    val rewardCompletedCount: Int = 0,
    val rewardTotalCount: Int = 3
)
