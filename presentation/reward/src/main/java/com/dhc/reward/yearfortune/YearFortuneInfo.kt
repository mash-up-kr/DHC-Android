package com.dhc.reward.yearfortune

import com.dhc.common.ImageResource
import com.dhc.designsystem.tipcard.TipCardModel
import com.dhc.presentation.ui.monetaryDetail.FortuneCard
import com.dhc.presentation.ui.monetaryDetail.ScoreInfo

data class YearFortuneInfo(
    val scoreInfo: ScoreInfo = ScoreInfo(),
    val fortuneCard: FortuneCard = FortuneCard(),
    val overallFortune: String = "",
    val quickViewFortune: List<QuickViewFortuneItem> = listOf(),
    val fiveElementData: List<FiveElementData> = listOf(),
    val energyChange: String = "",
    val yearTips: List<TipCardModel> = listOf(),
)

data class QuickViewFortuneItem(
    val title: String,
    val content: String,
    val icon: ImageResource? = null,
)
