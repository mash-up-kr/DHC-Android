package com.dhc.reward.yearfortune

import com.dhc.common.ImageResource
import com.dhc.designsystem.R
import com.dhc.designsystem.tipcard.TipCardModel
import com.dhc.dhcandroid.model.YearlyFortuneResponse
import com.dhc.presentation.ui.monetaryDetail.FortuneCard
import com.dhc.presentation.ui.monetaryDetail.ScoreInfo

internal fun YearlyFortuneResponse.toYearFortuneInfo(): YearFortuneInfo {
    return YearFortuneInfo(
        scoreInfo = ScoreInfo(
            date = "${year}년 운세 총평",
            score = totalScore,
            description = summaryTitle
        ),
        fortuneCard = FortuneCard(
            title = summaryTitle,
            message = "",
            image = summaryImageUrl.takeIf { it.isNotEmpty() }?.let { ImageResource.Url(it) }
                ?: ImageResource.Drawable(R.drawable.fortune_card_sample)
        ),
        overallFortune = summaryDetail,
        quickViewFortune = listOfNotNull(
            fortuneOverview.money.takeIf { it.title.isNotEmpty() }?.let {
                QuickViewFortuneItem(
                    title = it.title,
                    content = it.description,
                    icon = ImageResource.Drawable(R.drawable.ico_money_pocket)
                )
            },
            fortuneOverview.love.takeIf { it.title.isNotEmpty() }?.let {
                QuickViewFortuneItem(
                    title = it.title,
                    content = it.description,
                    icon = ImageResource.Drawable(R.drawable.ico_heart_target)
                )
            },
            fortuneOverview.study.takeIf { it.title.isNotEmpty() }?.let {
                QuickViewFortuneItem(
                    title = it.title,
                    content = it.description,
                    icon = ImageResource.Drawable(R.drawable.ico_study)
                )
            }
        ),
        fiveElementData = listOf(
            FiveElementData.fromPercentage(
                FiveElementType.WOOD,
                fiveElements.wood.percentage
            ),
            FiveElementData.fromPercentage(
                FiveElementType.FIRE,
                fiveElements.fire.percentage
            ),
            FiveElementData.fromPercentage(
                FiveElementType.EARTH,
                fiveElements.earth.percentage
            ),
            FiveElementData.fromPercentage(
                FiveElementType.METAL,
                fiveElements.metal.percentage
            ),
            FiveElementData.fromPercentage(
                FiveElementType.WATER,
                fiveElements.water.percentage
            )
        ),
        energyChange = buildString {
            if (yearlyEnergyTitle.isNotEmpty()) {
                append(yearlyEnergyTitle)
            }
            if (yearlyEnergyDetail.isNotEmpty()) {
                if (isNotEmpty()) append("\n\n")
                append(yearlyEnergyDetail)
            }
        },
        yearTips = listOfNotNull(
            tips.luckyMenu.takeIf { it.isNotEmpty() }?.let {
                TipCardModel(
                    title = "추천메뉴",
                    icon = ImageResource.Drawable(R.drawable.ico_knife),
                    color = null,
                    cont = it
                )
            },
            tips.luckyColor.takeIf { it.isNotEmpty() }?.let {
                TipCardModel(
                    title = "행운의 색상",
                    icon = ImageResource.Drawable(R.drawable.ico_clover),
                    color = tips.luckyColorHex.takeIf { hex -> hex.isNotEmpty() },
                    cont = it
                )
            },
            tips.unluckyMenu.takeIf { it.isNotEmpty() }?.let {
                TipCardModel(
                    title = "피해야 할 음식",
                    icon = ImageResource.Drawable(R.drawable.ico_green_face),
                    color = null,
                    cont = it
                )
            },
            tips.unluckyColor.takeIf { it.isNotEmpty() }?.let {
                TipCardModel(
                    title = "피해야 할 색상",
                    icon = ImageResource.Drawable(R.drawable.ico_red_face),
                    color = tips.unluckyColorHex.takeIf { hex -> hex.isNotEmpty() },
                    cont = it
                )
            }
        )
    )
}
