package com.dhc.dhcandroid.model

import kotlinx.serialization.Serializable

@Serializable
data class UserProfile(
    val id: String = "",
    val userToken: String = "",
    val gender: Gender = Gender.UNKNOWN,
    val birthDate: BirthDate? = null,
    val birthTime: String? = null,
    val preferredMissionCategoryList: List<MissionCategory> = emptyList(),
)

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
