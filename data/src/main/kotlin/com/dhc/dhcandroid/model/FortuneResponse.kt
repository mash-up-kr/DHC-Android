package com.dhc.dhcandroid.model

import kotlinx.serialization.Serializable

@Serializable
data class FortuneResponse(
    val date: String = "",
    val fortuneTitle: String = "",
    val fortuneDetail: String = "",
    val fortuneCardImage: String = "",
    val jinxedColor: String = "",
    val jinxedColorHex: String = "",
    val jinxedColorImage: String = "",
    val jinxedMenu: String = "",
    val jinxedMenuImage: String = "",
    val jinxedNumber: Int = 0,
    val luckyColor: String = "",
    val luckyColorHex: String = "",
    val luckyColorImage: String = "",
    val luckyNumber: Int = 0,
    val positiveScore: Int = 0,
    val negativeScore: Int = 0,
    val totalScore: Int = 0,
    val todayMenu: String = "",
    val todayMenuImage: String = "",
    val luckyColorType: FortuneColor = FortuneColor.UNKNOWN,
    val jinxedColorType: FortuneColor = FortuneColor.UNKNOWN
)

@Serializable
enum class FortuneColor {
    RED, YELLOW, GREEN, WHITE, BLACK, UNKNOWN
}
