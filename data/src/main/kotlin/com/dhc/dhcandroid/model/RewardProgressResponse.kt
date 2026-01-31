package com.dhc.dhcandroid.model

import kotlinx.serialization.Serializable

@Serializable
data class RewardProgressResponse(
    val user: RewardUser = RewardUser(),
    val rewardList: List<RewardItem> = emptyList()
)

@Serializable
data class RewardUser(
    val rewardImageUrl: String = "",
    val rewardLevel: String = "",
    val totalExp: Int = 0
)

@Serializable
data class RewardItem(
    val id: Int = 0,
    val title: String = "",
    val isUnlocked: Boolean = false,
)
