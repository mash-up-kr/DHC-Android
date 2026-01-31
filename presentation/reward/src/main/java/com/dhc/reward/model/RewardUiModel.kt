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
    val totalExp: Int = 0
) {
    companion object {
        fun from(rewardUser: com.dhc.dhcandroid.model.RewardUser): RewardUserUiModel {
            return RewardUserUiModel(
                rewardImageUrl = rewardUser.rewardImageUrl,
                rewardLevel = rewardUser.rewardLevel,
                totalExp = rewardUser.totalExp
            )
        }
    }
}

data class RewardItemUiModel(
    val id: Int = 0,
    val title: String = "",
    val isUnlocked: Boolean = false,
) {
    companion object {
        fun from(rewardItem: com.dhc.dhcandroid.model.RewardItem): RewardItemUiModel {
            return RewardItemUiModel(
                id = rewardItem.id,
                title = rewardItem.title,
                isUnlocked = rewardItem.isUnlocked
            )
        }
    }
}
