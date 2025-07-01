package com.dhc.home.model

import androidx.annotation.StringRes
import com.dhc.home.R

enum class FinishMissionToast(@StringRes val messageRes: Int) {
    MESSAGE_1(R.string.finish_mission_toast_1),
    MESSAGE_2(R.string.finish_mission_toast_2),
    MESSAGE_3(R.string.finish_mission_toast_3),
    MESSAGE_4(R.string.finish_mission_toast_4),
    MESSAGE_5(R.string.finish_mission_toast_5);

    companion object {
        fun getRandomMessage(): Int = FinishMissionToast.entries.toTypedArray().random().messageRes
    }
}