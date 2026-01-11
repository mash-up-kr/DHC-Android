package com.dhc.home.model

enum class MissionFailType(
    val mainMessage: String,
    val subMessage: String
) {
    NORMAL(
        mainMessage = "아쉽게 미션을 실패했네요...",
        subMessage = "하지만 내일 미션을 성공하면 리워드가 두배에요!"
    ),
    NEXT_DAY_REENTRY(
        mainMessage = "아쉽게 어제 미션을 실패했어요...",
        subMessage = "하지만 오늘 미션을 성공하면 리워드가 두배에요!"
    ),
    LONG_ABSENCE(
        mainMessage = "며칠 미션을 못 하셨지만...\n오늘 하면 메꿔드릴게요!",
        subMessage = "특별히 오늘 미션을 완수하면\n보상을 4배나 드려요"
    )
}