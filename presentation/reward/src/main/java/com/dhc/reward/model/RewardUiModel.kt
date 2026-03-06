package com.dhc.reward.model

import com.dhc.dhcandroid.model.RewardProgressResponse

data class RewardUiModel(
    val user: RewardUserUiModel = RewardUserUiModel(),
    val rewardList: List<RewardItemUiModel> = emptyList()
) {
    companion object {
        fun from(response: RewardProgressResponse): RewardUiModel {
            return RewardUiModel(
                user = RewardUserUiModel.from(response.user),
                rewardList = response.rewardList.map { RewardItemUiModel.from(it) }
            )
        }
    }
}

data class RewardUserUiModel(
    val rewardImageUrl: String = "",
    val rewardLevel: String = "",
    val rewardLevelName: String = "",
    val totalPoint: Int = 0,
    val currentLevelPoint: Int = 0,
    val nextLevelRequiredPoint: Int = 0
) {
    companion object {
        fun from(rewardUser: com.dhc.dhcandroid.model.RewardUser): RewardUserUiModel {
            return RewardUserUiModel(
                rewardImageUrl = rewardUser.rewardImageUrl,
                rewardLevel = "LV${rewardUser.rewardLevel.level}",
                rewardLevelName = rewardUser.rewardLevel.name,
                totalPoint = rewardUser.totalPoint,
                currentLevelPoint = rewardUser.currentLevelPoint,
                nextLevelRequiredPoint = rewardUser.nextLevelRequiredPoint
            )
        }
    }
}

data class RewardItemUiModel(
    val id: Int = 0,
    val title: String = "",
    val isUnlocked: Boolean = false,
    val isUsed: Boolean = false,
    val message: String = "",
) {
    companion object {
        fun from(rewardItem: com.dhc.dhcandroid.model.RewardItem): RewardItemUiModel {
            return RewardItemUiModel(
                id = rewardItem.id,
                title = rewardItem.title,
                isUnlocked = rewardItem.isUnlocked,
                isUsed = rewardItem.isUsed,
                message = rewardItem.message,
            )
        }
    }
}
