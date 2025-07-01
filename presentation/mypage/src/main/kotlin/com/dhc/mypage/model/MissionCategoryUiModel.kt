package com.dhc.mypage.model

import com.dhc.dhcandroid.model.MissionCategory
import com.dhc.dhcandroid.model.MissionCategoryResponse

data class MissionCategoryUiModel(
    val name: MissionCategory? = null,
    val displayName: String = "",
    val imageUrl: String = "",
) {
    companion object {
        fun from(missionCategoryResponse: MissionCategoryResponse) =
            MissionCategoryUiModel(
                name = missionCategoryResponse.name,
                displayName = missionCategoryResponse.displayName,
                imageUrl = missionCategoryResponse.imageUrl
            )
    }
}