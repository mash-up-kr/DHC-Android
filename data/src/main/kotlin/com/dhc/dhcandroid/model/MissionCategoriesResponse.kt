package com.dhc.dhcandroid.model

import kotlinx.serialization.Serializable

@Serializable
data class MissionCategoriesResponse(
    val categories: List<MissionCategoryResponse> = emptyList(),
)

@Serializable
data class MissionCategoryResponse(
    val name: MissionCategory = MissionCategory.UNKNOWN,
    val displayName: String = "",
    val image: String = "",
)
