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
        title = cardInfo.title,
        message = cardInfo.subTitle,
        image = ImageResource.Url(cardInfo.image),
    ),
    monetaryDetail = this.fortuneDetail,
    todayTips = tips.map { tip ->
        TipCardModel(
            title = tip.title,
            icon = ImageResource.Url(tip.image),
            cont = tip.description,
            color = tip.hexColor,
        )
    }
)
