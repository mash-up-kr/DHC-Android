package com.dhc.home.detail.utils

import com.dhc.common.FormatterUtil
import com.dhc.common.ImageResource
import com.dhc.designsystem.tipcard.TipCardModel
import com.dhc.dhcandroid.model.FortuneResponse
import com.dhc.presentation.ui.monetaryDetail.FortuneCard
import com.dhc.presentation.ui.monetaryDetail.MonetaryLuckInfo
import com.dhc.presentation.ui.monetaryDetail.ScoreInfo
import java.time.LocalDate

internal fun FortuneResponse.toMonetaryLuckInfo() = MonetaryLuckInfo(
    scoreInfo = ScoreInfo(
        date = LocalDate.parse(this.date, FormatterUtil.dhcYearMonthDayFormat)
            .format(FormatterUtil.dhcDateFormat),
        score = this.totalScore,
        description = this.fortuneTitle,
    ),
    fortuneCard = FortuneCard(
        image = fortuneCardImage,
        message = "" // Todo :: 카드 메세지 필요,
    ),
    monetaryDetail = this.fortuneDetail,
    todayTips = listOf(
        TipCardModel(
            title = "오늘의 추천 메뉴",
            icon = ImageResource.Url(todayMenuImage),
            cont = todayMenu,
            color = null,
        ),
        TipCardModel(
            title = "행운의 색상",
            icon = ImageResource.Url(luckyColorImage),
            cont = luckyColor,
            color = luckyColorHex,
        ),
        TipCardModel(
            title = "피해야 할 음식",
            icon = ImageResource.Url(jinxedMenuImage),
            cont = jinxedMenu,
            color = null,
        ),
        TipCardModel(
            title = "피해야 할 색상",
            icon = ImageResource.Url(jinxedColorImage),
            cont = jinxedColor,
            color = jinxedColorHex,
        ),
    )
)
