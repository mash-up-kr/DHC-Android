package com.dhc.dhcandroid.model

import kotlinx.serialization.Serializable

@Serializable
data class MyPageResponse(
    val animalCard: AnimalCard = AnimalCard(),
    val birthDate: BirthDate? = null,
    val birthTime: String? = null,
    val preferredMissionCategoryList: List<MissionCategory> = emptyList(),
    val alarm: Boolean = false,
)

@Serializable
data class AnimalCard(
    val name: String = "",
    val cardImageUrl: String? = null
)
