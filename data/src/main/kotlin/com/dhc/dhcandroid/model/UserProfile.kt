package com.dhc.dhcandroid.model

import kotlinx.serialization.Serializable

@Serializable
data class UserProfile(
    val id: String,
    val userToken: String,
    val gender: Gender,
    val birthDate: BirthDate?,
    val birthTime: String?,
    val preferredMissionCategoryList: List<MissionCategory>,
) {
    companion object {
        val Empty = UserProfile(
            id = "",
            userToken = "",
            gender = Gender.UNKNOWN,
            birthDate = null,
            birthTime = null,
            preferredMissionCategoryList = emptyList(),
        )
    }
}

@Serializable
enum class Gender {
    UNKNOWN,
    MALE,
    FEMALE
}

@Serializable
data class BirthDate(
    val date: String = "",
    val calendarType: CalendarType = CalendarType.UNKNOWN,
)

@Serializable
enum class CalendarType {
    UNKNOWN,
    SOLAR,
    LUNAR,
}

@Serializable
enum class MissionCategory {
    UNKNOWN,
    TRANSPORTATION,
    FOOD,
    DIGITAL,
    SHOPPING,
    TRAVEL,
    SOCIAL,
}
