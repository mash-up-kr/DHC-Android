package com.dhc.home.model

import com.dhc.dhcandroid.model.DailyFortune

data class TodayDailyFortuneUiModel(
    val date: String = "",
    val fortuneTitle: String = "",
    val fortuneDetail: String = "",
    val score: Int = 0,
) {
    companion object {
        fun from(fortune: DailyFortune): TodayDailyFortuneUiModel = TodayDailyFortuneUiModel(
            date = fortune.date,
            fortuneTitle = fortune.fortuneTitle,
            fortuneDetail = fortune.fortuneDetail,
            score = fortune.score
        )
    }
}
