package com.dhc.dhcandroid.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Mission(
    val missionId: String = "",
    val category: MissionCategory = MissionCategory.TRANSPORTATION,
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
    @SerialName("LONG_TERM")
    LONG_TERM,

    @SerialName("DAILY")
    DAILY,
}
