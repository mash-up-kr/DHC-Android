package com.dhc.home.model

import com.dhc.dhcandroid.model.Mission
import com.dhc.dhcandroid.model.MissionType
import com.dhc.home.model.MissionUiModel.Companion.toDDay
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import kotlin.math.abs

data class MissionUiModel(
    val missionId: String = "",
    val category: String = "",
    val type: MissionType = MissionType.LONG_TERM,
    val finished: Boolean = false,
    val title: String = "",
    val endDate: String = "",
    val difficulty: String = "",
    val switchCount: Int = 0,
    val isBlink: Boolean = false,
    val isExpanded: Boolean = false
) {
    companion object {

        fun from(mission: Mission): MissionUiModel = MissionUiModel(
            missionId = mission.missionId,
            category = mission.category,
            type = mission.type,
            finished = mission.finished,
            title = mission.title,
            switchCount = mission.switchCount,
            endDate = mission.endDate.toDDay(),
            difficulty = MissionDifficulty.from(mission.difficulty)
        )

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
    endDate = endDate.toDDay(),
    difficulty = MissionDifficulty.from(difficulty),
    switchCount = switchCount,
)