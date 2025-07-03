package com.dhc.home.model

enum class FinishMissionToast(val messageRes: String) {
    MESSAGE_1("차근차근 잘하고 있어요!"),
    MESSAGE_2("시작이 반이죠! 잘하고 있어요"),
    MESSAGE_3("멋져요! 오늘도 발전하는 하루에요"),
    MESSAGE_4("점점 부자가 되고 있어요!"),
    MESSAGE_5("작은 절약이 큰 변화를 만들어요");

    companion object {
        fun getRandomMessage(): String = FinishMissionToast.entries.toTypedArray().random().messageRes
    }
}