package com.dhc.dhcandroid.model

import kotlinx.serialization.Serializable

@Serializable
data class MyPageResponse(
    val animalCard: AnimalCard = AnimalCard(),
    val birthDate: BirthDate? = null,
    val birthTime: String? = null,
    val preferredMissionCategoryList: List<MissionCategoryResponse> = emptyList(),
    val alarm: Boolean = false,
    val fortuneTests: List<FortuneTestInfo> = emptyList(),
)

@Serializable
data class AnimalCard(
    val name: String = "",
    val cardImage: String? = null
)

@Serializable
data class FortuneTestInfo(
    val image: String = "",
    val displayName: String = "",
    val testUrl: String = "",
)
