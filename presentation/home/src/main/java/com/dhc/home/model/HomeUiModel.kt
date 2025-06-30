package com.dhc.home.model

import com.dhc.dhcandroid.model.HomeViewResponse
import com.dhc.dhcandroid.model.Mission
import com.dhc.dhcandroid.model.MissionType
import com.dhc.home.model.MissionUiModel.Companion.calDifficulty
import com.dhc.home.model.MissionUiModel.Companion.toDDay
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import kotlin.math.abs

data class HomeUiModel(
    val longTermMission: MissionUiModel = MissionUiModel(),
    val todayDailyMissionList: List<MissionUiModel> = emptyList(),
    val todayDailyFortune: TodayDailyFortuneUiModel = TodayDailyFortuneUiModel()
) {
    companion object {
        fun from(homeViewResponse: HomeViewResponse) = HomeUiModel(
            longTermMission = MissionUiModel(
                missionId = homeViewResponse.longTermMission.missionId,
                category = homeViewResponse.longTermMission.category,
                type = homeViewResponse.longTermMission.type,
                finished = homeViewResponse.longTermMission.finished,
                title = homeViewResponse.longTermMission.title,
                switchCount = homeViewResponse.longTermMission.switchCount,
                endDate = homeViewResponse.longTermMission.endDate.toDDay(),
                difficulty = homeViewResponse.longTermMission.difficulty.calDifficulty()
            ),
            todayDailyMissionList = homeViewResponse.todayDailyMissionList.map {
                MissionUiModel(
                    missionId = it.missionId,
                    category = it.category,
                    type = it.type,
                    finished = it.finished,
                    title = it.title,
                    switchCount = it.switchCount,
                    endDate = it.endDate.toDDay(),
                    difficulty = it.difficulty.calDifficulty()
                )
            },
            todayDailyFortune = TodayDailyFortuneUiModel(
                date = homeViewResponse.todayDailyFortune.date,
                fortuneTitle = homeViewResponse.todayDailyFortune.fortuneTitle,
                fortuneDetail = homeViewResponse.todayDailyFortune.fortuneDetail,
                score = homeViewResponse.todayDailyFortune.score,
            )
        )
    }
}
