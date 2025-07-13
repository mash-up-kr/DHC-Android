package com.dhc.home.model

import com.dhc.dhcandroid.model.DailyFortuneResponse

data class TodayDailyFortuneUiModel(
    val date: String = "",
    val fortuneTitle: String = "",
    val score: Int = 0,
    val fortuneCardImage: String = "",
    val fortuneCardTitle: String = "",
    val fortuneCardSubTitle: String = "",
) {
    companion object {
        fun from(fortune: DailyFortuneResponse): TodayDailyFortuneUiModel = TodayDailyFortuneUiModel(
            date = fortune.date,
            fortuneTitle = fortune.fortuneTitle,
            score = fortune.totalScore,
            fortuneCardImage = fortune.fortuneCardImage,
            fortuneCardTitle = fortune.fortuneCardTitle,
            fortuneCardSubTitle = fortune.fortuneCardSubTitle
        )
    }
}
