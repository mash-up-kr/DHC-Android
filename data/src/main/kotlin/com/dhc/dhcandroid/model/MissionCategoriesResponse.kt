package com.dhc.dhcandroid.model

import kotlinx.serialization.Serializable

@Serializable
data class MissionCategoriesResponse(
    val categories: List<MissionCategoryResponse> = emptyList(),
)

@Serializable
data class MissionCategoryResponse(
    val name: MissionCategory? = null,
    val displayName: String = "",
    val imageUrl: String = "",
)
