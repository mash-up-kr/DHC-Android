package com.dhc.dhcandroid.model

import kotlinx.serialization.Serializable

@Serializable
data class MissionsResponse(
    val missions: List<Mission> = listOf()
)

@Serializable
data class Mission(
    val missionId: String = "",
    val category: String = "",
    val difficulty: Int = 0,
    val type: MissionType = MissionType.LONG_TERM,
    val finished: Boolean = false,
    val cost: String = "",
    val endDate: String = "",
    val title: String = "",
    val switchCount: Int = 0,
)

@Serializable
enum class MissionType {
    LONG_TERM,
    DAILY,
}
