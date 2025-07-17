package com.dhc.missionstatus.ui

import com.dhc.dhcandroid.model.AnalysisMonthViewResponse
import java.time.LocalDate

data class MissionAnalysisUiModel(
    val currentMonth: Int,
    val averageSucceedProbability: Int,
    val easterEggBirthDay: LocalDate? = null,
) {
    companion object{
        fun from(data: AnalysisMonthViewResponse?) = data?.let {
            MissionAnalysisUiModel(
                currentMonth = data.month,
                averageSucceedProbability = data.averageSucceedProbability,
            )
        }
    }
}
