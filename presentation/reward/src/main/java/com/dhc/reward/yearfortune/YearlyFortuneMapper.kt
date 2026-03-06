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
            title = cardInfo.title,
            message = cardInfo.subTitle,
            image = cardInfo.image.takeIf { it.isNotEmpty() }?.let { ImageResource.Url(it) }
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
        yearTips = tips.mapNotNull { tip ->
            tip.description.takeIf { it.isNotEmpty() }?.let {
                val (icon, color) = when (tip.title) {
                    "행운의 메뉴" -> Pair(ImageResource.Drawable(R.drawable.ico_knife), null)
                    "행운의 색상" -> Pair(
                        ImageResource.Drawable(R.drawable.ico_clover),
                        tip.hexColor.takeIf { hex -> hex.isNotEmpty() }
                    )
                    "피해야 할 색상" -> Pair(
                        ImageResource.Drawable(R.drawable.ico_red_face),
                        tip.hexColor.takeIf { hex -> hex.isNotEmpty() }
                    )
                    "피해야 할 음식" -> Pair(ImageResource.Drawable(R.drawable.ico_green_face), null)
                    else -> Pair(null, null)
                }
                TipCardModel(
                    title = tip.title,
                    icon = icon,
                    color = color,
                    cont = it
                )
            }
        }
    )
}
