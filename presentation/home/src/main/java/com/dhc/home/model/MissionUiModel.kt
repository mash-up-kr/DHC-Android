package com.dhc.home.model

import com.dhc.dhcandroid.model.Mission
import com.dhc.dhcandroid.model.MissionType
import com.dhc.home.model.MissionUiModel.Companion.calDifficulty
import com.dhc.home.model.MissionUiModel.Companion.toDDay
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import kotlin.math.abs

data class MissionUiModel(
    val missionId: String = "",
    val category: String = "",
    val type: MissionType = MissionType.LONG_TERM,
    val isChecked: Boolean = false,
    val title: String = "",
    val endDate: String = "",
    val difficulty: String = "",
    val switchCount: Int = 0,
    val isBlink: Boolean = false,
    val isExpanded: Boolean = false
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
    isChecked = finished.not(),
    title = title,
    endDate = endDate.toDDay(),
    difficulty = difficulty.calDifficulty(),
    switchCount = switchCount,
)



fun getMissionIdList(longTermMission: MissionUiModel, todayDailyMissionList: List<MissionUiModel>): List<String> {
    return listOf(longTermMission.missionId) + todayDailyMissionList.map { it.missionId }
}
