package com.dhc.intro.category.model

import com.dhc.dhcandroid.model.MissionCategory

data class CategoryItem(
    val name: MissionCategory = MissionCategory.UNKNOWN,
    val displayName: String = "",
    val imageUrl: String = "",
)
