package com.dhc.home.model

import com.dhc.dhcandroid.model.HomeViewResponse
import com.dhc.dhcandroid.model.Mission
import com.dhc.dhcandroid.model.MissionCategory
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

data class MissionUiModel(
    val missionId: String = "",
    val category: String = "",
    val type: MissionType = MissionType.LONG_TERM,
    val finished: Boolean = false,
    val title: String = "",
    val endDate: String = "",
    val difficulty: String = "",
    val switchCount: Int = 0,
) {
    companion object {
        fun Int.calDifficulty(): String {
            return when (this) {
                1 -> "Easy"
                2 -> "Medium"
                3 -> "Hard"
                else -> "Easy"
            }
        }

        fun String.toDDay(): String {
            return try {
                val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                val targetDate = LocalDate.parse(this, formatter)
                val today = LocalDate.now()

                val daysBetween = ChronoUnit.DAYS.between(today, targetDate)

                when {
                    daysBetween > 0 -> "D-${daysBetween}"
                    daysBetween == 0L -> "D-Day"
                    else -> "D+${abs(daysBetween)}"
                }
            } catch (e: Exception) {
                "Invalid Date"
            }
        }
    }
}

fun Mission.toUiModel() = MissionUiModel(
    missionId = missionId,
    category = category,
    type = type,
    finished = finished,
    title = title,
    endDate = endDate,
    difficulty = difficulty.calDifficulty(),
    switchCount = switchCount,
)

data class TodayDailyFortuneUiModel(
    val date: String = "",
    val fortuneTitle: String = "",
    val fortuneDetail: String = "",
    val score: Int = 0,
)

fun List<Mission>.toUiModel() = map {
    MissionUiModel(
        missionId = it.missionId,
        category = it.category,
        type = it.type,
        finished = it.finished,
        title = it.title,
        endDate = it.endDate,
        difficulty = it.difficulty.calDifficulty(),
        switchCount = it.switchCount,
    )
}

fun getMissionIdList(longTermMission: MissionUiModel, todayDailyMissionList: List<MissionUiModel>): List<String> {
    return listOf(longTermMission.missionId) + todayDailyMissionList.map { it.missionId }
}
