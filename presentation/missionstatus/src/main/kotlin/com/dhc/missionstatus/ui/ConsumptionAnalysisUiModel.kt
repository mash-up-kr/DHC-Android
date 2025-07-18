package com.dhc.missionstatus.ui

import com.dhc.dhcandroid.model.AnalysisViewResponse
import com.dhc.dhcandroid.model.GenerationMoneyViewResponse
import com.dhc.missionstatus.utils.getGenderString
import com.dhc.missionstatus.utils.toIntOrZero

data class ConsumptionAnalysisUiModel(
    val totalSaveMoney: Int = 0,
    val weeklySpendMoney: Int = 0,
    val graphData: AnalysisGraphUiModel = AnalysisGraphUiModel(),
) {
    companion object {
        fun from(analysisViewResponse: AnalysisViewResponse) =
            ConsumptionAnalysisUiModel(
                totalSaveMoney = analysisViewResponse.totalSavedMoney.toIntOrZero(),
                weeklySpendMoney = analysisViewResponse.weeklySpendMoney.toIntOrZero(),
                graphData = AnalysisGraphUiModel.from(analysisViewResponse.generationMoneyViewResponse)
            )
    }
}

data class AnalysisGraphUiModel(
    val target: String = "",
    val targetData: Int = 0,
) {
    companion object {
        fun from(generationMoneyViewResponse: GenerationMoneyViewResponse) =
            AnalysisGraphUiModel(
                target = "${generationMoneyViewResponse.generation} ${generationMoneyViewResponse.gender.getGenderString()}",
                targetData = generationMoneyViewResponse.averageSpendMoney.toIntOrZero()
            )
    }
}

