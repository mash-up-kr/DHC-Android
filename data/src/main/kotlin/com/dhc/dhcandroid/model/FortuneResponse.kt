package com.dhc.dhcandroid.model

import kotlinx.serialization.Serializable

@Serializable
data class FortuneResponse(
    val date: String = "",
    val fortuneTitle: String = "",
    val fortuneDetail: String = "",
    val totalScore: Int = 0,
    val cardInfo: FortuneCardInfo = FortuneCardInfo(),
    val tips: List<FortuneTips> = emptyList<FortuneTips>(),
)

@Serializable
enum class FortuneColor {
    RED, YELLOW, GREEN, WHITE, BLACK, UNKNOWN
}


@Serializable
data class FortuneCardInfo(
    val imageURL: String = "",
    val title: String = "",
    val subTitle: String = "",
)

@Serializable
data class FortuneTips(
    val imageURL: String = "",
    val title: String = "",
    val description: String = "",
    val hexColor: String? = null
)

