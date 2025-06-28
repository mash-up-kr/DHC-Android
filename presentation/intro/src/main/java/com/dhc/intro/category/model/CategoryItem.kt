package com.dhc.intro.category.model

import com.dhc.dhcandroid.model.MissionCategory

data class CategoryItem(
    val name: MissionCategory? = null,
    val displayName: String = "",
    val imageUrl: String = "",
) {
    fun toServerType(): MissionCategory = MissionCategory.TRANSPORTATION // Todo : 임시 코드
}
