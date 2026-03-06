package com.dhc.dhcandroid.model

import kotlinx.serialization.Serializable

@Serializable
data class YearlyFortuneResponse(
    val year: Int = 0,
    val generatedDate: String = "",
    val totalScore: Int = 0,
    val summaryTitle: String = "",
    val summaryDetail: String = "",
    val fortuneOverview: FortuneOverview = FortuneOverview(),
    val fiveElements: FiveElements = FiveElements(),
    val yearlyEnergyTitle: String = "",
    val yearlyEnergyDetail: String = "",
    val tips: List<TipItem> = emptyList(),
    val cardInfo: CardInfo = CardInfo()
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
    val description: String = "",
    val image: String = ""
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
data class TipItem(
    val image: String = "",
    val title: String = "",
    val description: String = "",
    val hexColor: String = ""
)

@Serializable
data class CardInfo(
    val image: String = "",
    val title: String = "",
    val subTitle: String = ""
)
