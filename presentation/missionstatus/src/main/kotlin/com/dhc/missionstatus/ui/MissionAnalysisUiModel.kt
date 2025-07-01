package com.dhc.missionstatus.ui

import com.dhc.dhcandroid.model.AnalysisMonthViewResponse

data class MissionAnalysisUiModel(
    val currentMonth: Int,
    val averageSucceedProbability: Int,
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
