package com.dhc.dhcandroid.model

import kotlinx.serialization.Serializable

@Serializable
data class UserProfile(
    val id: String = "",
    val userToken: String = "",
    val gender: Gender = Gender.MALE,
    val birthDate: BirthDate? = null,
    val birthTime: String? = null,
    val preferredMissionCategoryList: List<MissionCategory> = emptyList(),
)

@Serializable
enum class Gender {
    MALE,
    FEMALE
}

@Serializable
data class BirthDate(
    val date: String = "",
    val calendarType: CalendarType = CalendarType.SOLAR,
)

@Serializable
enum class CalendarType {
    SOLAR,
    LUNAR,
}

@Serializable
enum class MissionCategory {
    TRANSPORTATION,
    FOOD,
    DIGITAL,
    SHOPPING,
    TRAVEL,
    SOCIAL,
}
