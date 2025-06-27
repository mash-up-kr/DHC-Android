package com.dhc.dhcandroid.model

import kotlinx.serialization.Serializable

@Serializable
data class DailyFortune(
    val date: String = "",
    val fortuneTitle: String = "",
    val fortuneDetail: String = "",
    val jinxedColor: String = "",
    val jinxedColorHex: String = "",
    val jinxedMenu: String = "",
    val jinxedNumber: Int = 0,
    val luckyColor: String = "",
    val luckyColorHex: String = "",
    val luckyNumber: Int = 0,
    val score: Int = 0,
    val todayMenu: String = ""
)
