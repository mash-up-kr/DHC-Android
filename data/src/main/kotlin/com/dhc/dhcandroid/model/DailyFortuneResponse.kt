package com.dhc.dhcandroid.model

import kotlinx.serialization.Serializable

@Serializable
data class DailyFortuneResponse(
    val date: String = "",
    val fortuneTitle: String = "",
    val totalScore: Int = 0,
    val positiveScore: Int = 0,
    val negativeScore: Int = 0,
    val cardInfo: FortuneCardInfo = FortuneCardInfo(),
)
