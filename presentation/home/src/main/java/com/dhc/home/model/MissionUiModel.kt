package com.dhc.home.model

import com.dhc.common.FormatterUtil.toDDay
import com.dhc.dhcandroid.model.Mission
import com.dhc.dhcandroid.model.MissionType

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