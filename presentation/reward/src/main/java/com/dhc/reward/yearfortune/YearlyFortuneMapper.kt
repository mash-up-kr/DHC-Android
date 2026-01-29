package com.dhc.reward.yearfortune

import com.dhc.designsystem.tipcard.TipCardModel
import com.dhc.dhcandroid.model.YearlyFortuneResponse
import com.dhc.presentation.ui.monetaryDetail.FortuneCard
import com.dhc.presentation.ui.monetaryDetail.ScoreInfo

internal fun YearlyFortuneResponse.toYearFortuneInfo(): YearFortuneInfo {
    return YearFortuneInfo(
        scoreInfo = ScoreInfo(
            date = "${year}년 운세 총평",
            score = totalScore,
            description = summaryDetail
        ),
        fortuneCard = FortuneCard(
            title = summaryTitle,
            message = "",
            image = null
        ),
        overallFortune = summaryDetail,
        quickViewFortune = listOfNotNull(
            fortuneOverview.money.takeIf { it.title.isNotEmpty() }?.let {
                QuickViewFortuneItem(
                    title = it.title,
                    content = it.description,
                    icon = null
                )
            },
            fortuneOverview.love.takeIf { it.title.isNotEmpty() }?.let {
                QuickViewFortuneItem(
                    title = it.title,
                    content = it.description,
                    icon = null
                )
            },
            fortuneOverview.study.takeIf { it.title.isNotEmpty() }?.let {
                QuickViewFortuneItem(
                    title = it.title,
                    content = it.description,
                    icon = null
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
                    icon = null,
                    color = null,
                    cont = it
                )
            },
            tips.luckyColor.takeIf { it.isNotEmpty() }?.let {
                TipCardModel(
                    title = "행운의 색상",
                    icon = null,
                    color = tips.luckyColorHex.takeIf { hex -> hex.isNotEmpty() },
                    cont = it
                )
            },
            tips.unluckyMenu.takeIf { it.isNotEmpty() }?.let {
                TipCardModel(
                    title = "피해야 할 음식",
                    icon = null,
                    color = null,
                    cont = it
                )
            },
            tips.unluckyColor.takeIf { it.isNotEmpty() }?.let {
                TipCardModel(
                    title = "피해야 할 색상",
                    icon = null,
                    color = tips.unluckyColorHex.takeIf { hex -> hex.isNotEmpty() },
                    cont = it
                )
            }
        )
    )
}
