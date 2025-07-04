package com.dhc.presentation.ui.monetaryDetail

import com.dhc.common.ImageResource
import com.dhc.designsystem.tipcard.TipCardModel

data class MonetaryLuckInfo(
    val scoreInfo: ScoreInfo = ScoreInfo(),
    val fortuneCard: FortuneCard = FortuneCard(),
    val monetaryDetail: String = "",
    val todayTips: List<TipCardModel> = listOf(),
)

data class ScoreInfo(
    val date: String = "",
    val score: Int = 0,
    val description: String = "",
)

data class FortuneCard(
    val title: String = "",
    val message: String = "",
    val image: ImageResource? = null,
)
