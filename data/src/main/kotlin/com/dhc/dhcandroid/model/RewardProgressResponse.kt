package com.dhc.dhcandroid.model

import kotlinx.serialization.Serializable

@Serializable
data class RewardProgressResponse(
    val user: RewardUser = RewardUser(),
    val rewardList: List<RewardItem> = emptyList()
)

@Serializable
data class RewardUser(
    val image: String = "",
    val rewardLevel: RewardLevel = RewardLevel(),
    val totalPoint: Int = 0,
    val currentLevelPoint: Int = 0,
    val nextLevelRequiredPoint: Int = 0
)

@Serializable
data class RewardLevel(
    val level: Int = 1,
    val name: String = "",
    val requiredTotalPoint: Int = 0
)

@Serializable
data class RewardItem(
    val id: Int = 0,
    val title: String = "",
    val isUnlocked: Boolean = false,
    val isUsed: Boolean = false,
    val icon: String = "",
    val message: String = "",
)
