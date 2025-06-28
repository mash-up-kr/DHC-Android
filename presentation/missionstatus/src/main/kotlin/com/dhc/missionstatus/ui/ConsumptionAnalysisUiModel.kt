package com.dhc.missionstatus.ui

import com.dhc.dhcandroid.model.AnalysisViewResponse
import com.dhc.dhcandroid.model.GenerationMoneyViewResponse
import com.dhc.missionstatus.utils.getGenderString
import com.dhc.missionstatus.utils.getGenerationString
import com.dhc.missionstatus.utils.toWon

data class ConsumptionAnalysisUiModel(
    val totalSaveMoney: Int = 0,
    val weeklySaveMoney: Int = 0,
    val graphData: AnalysisGraphUiModel = AnalysisGraphUiModel(),
) {
    companion object {
        fun from(analysisViewResponse: AnalysisViewResponse) =
            ConsumptionAnalysisUiModel(
                totalSaveMoney = analysisViewResponse.totalSavedMoney.toWon(),
                weeklySaveMoney = analysisViewResponse.weeklySavedMoney.toWon(),
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
                target = "${generationMoneyViewResponse.generation.getGenerationString()} ${generationMoneyViewResponse.gender.getGenderString()}",
                targetData = generationMoneyViewResponse.averageSpendMoney.toWon()
            )
    }
}

