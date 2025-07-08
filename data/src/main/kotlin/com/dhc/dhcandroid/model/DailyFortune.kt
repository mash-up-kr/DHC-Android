package com.dhc.dhcandroid.model

import kotlinx.serialization.Serializable

@Serializable
data class DailyFortune(
    val date: String = "",
    val fortuneTitle: String = "",
    val fortuneDetail: String = "",
    val jinxedColor: String = "",
    val jinxedColorHex: String = "",
    val jinxedColorImage: String = "",
    val jinxedMenu: String = "",
    val jinxedNumber: Int = 0,
    val luckyColor: String = "",
    val luckyColorHex: String = "",
    val luckyColorImage: String = "",
    val todayMenuImage: String = "",
    val luckyNumber: Int = 0,
    val totalScore: Int = 0,
    val todayMenu: String = ""
)
