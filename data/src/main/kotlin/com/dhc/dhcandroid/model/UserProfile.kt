package com.dhc.dhcandroid.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserProfile(
    val id: String = "",
    val userToken: String = "",
    val gender: Gender = Gender.MALE,
    val birthDate: BirthDate? = null,
    val birthTime: String = "",
    val preferredMissionCategoryList: List<MissionCategory> = emptyList(),
)

@Serializable
enum class Gender {
    @SerialName("MALE")
    MALE,

    @SerialName("FEMALE")
    FEMALE
}

@Serializable
data class BirthDate(
    val date: String = "",
    val calendarType: CalendarType = CalendarType.SOLAR,
)

@Serializable
enum class CalendarType {
    @SerialName("SOLAR")
    SOLAR,

    @SerialName("LUNAR")
    LUNAR,
}

@Serializable
enum class MissionCategory {
    @SerialName("TRANSPORTATION")
    TRANSPORTATION,

    @SerialName("FOOD")
    FOOD,

    @SerialName("DIGITAL")
    DIGITAL,

    @SerialName("SHOPPING")
    SHOPPING,

    @SerialName("TRAVEL")
    TRAVEL,

    @SerialName("SOCIAL")
    SOCIAL,
}
