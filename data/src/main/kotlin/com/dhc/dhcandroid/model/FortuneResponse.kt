package com.dhc.dhcandroid.model

import kotlinx.serialization.Serializable

@Serializable
data class FortuneResponse(
    val date: String = "",
    val fortuneTitle: String = "",
    val fortuneDetail: String = "",
    val jinxedColor: String = "",
    val jinxedColorHex: String = "",
    val jinxedColorImageUrl: String = "",
    val jinxedMenu: String = "",
    val jinxedMenuImageUrl: String = "",
    val jinxedNumber: Int = 0,
    val luckyColor: String = "",
    val luckyColorHex: String = "",
    val luckyColorImageUrl: String = "",
    val luckyNumber: Int = 0,
    val positiveScore: Int = 0,
    val negativeScore: Int = 0,
    val totalScore: Int = 0,
    val todayMenu: String = "",
    val todayMenuImageUrl: String = "",
    val luckyColorType: FortuneColor = FortuneColor.UNKNOWN,
    val jinxedColorType: FortuneColor = FortuneColor.UNKNOWN
)

@Serializable
enum class FortuneColor {
    RED, YELLOW, GREEN, WHITE, BLACK, UNKNOWN
}
