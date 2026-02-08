package com.dhc.dhcandroid.model

import kotlinx.serialization.Serializable

@Serializable
data class YearlyFortuneResponse(
    val year: Int = 0,
    val generatedDate: String = "",
    val totalScore: Int = 0,
    val summaryTitle: String = "",
    val summaryDetail: String = "",
    val summaryImageUrl: String = "",
    val fortuneOverview: FortuneOverview = FortuneOverview(),
    val fiveElements: FiveElements = FiveElements(),
    val yearlyEnergyTitle: String = "",
    val yearlyEnergyDetail: String = "",
    val tips: YearlyTips = YearlyTips()
)

@Serializable
data class FortuneOverview(
    val money: FortuneItem = FortuneItem(),
    val love: FortuneItem = FortuneItem(),
    val study: FortuneItem = FortuneItem()
)

@Serializable
data class FortuneItem(
    val title: String = "",
    val description: String = ""
)

@Serializable
data class FiveElements(
    val dominantElement: String = "",
    val dominantWarning: String = "",
    val wood: ElementStatus = ElementStatus(),
    val fire: ElementStatus = ElementStatus(),
    val earth: ElementStatus = ElementStatus(),
    val metal: ElementStatus = ElementStatus(),
    val water: ElementStatus = ElementStatus()
)

@Serializable
data class ElementStatus(
    val percentage: Int = 0,
    val status: String = ""
)

@Serializable
data class YearlyTips(
    val luckyMenu: String = "",
    val luckyColor: String = "",
    val luckyColorHex: String = "",
    val unluckyMenu: String = "",
    val unluckyColor: String = "",
    val unluckyColorHex: String = ""
)
