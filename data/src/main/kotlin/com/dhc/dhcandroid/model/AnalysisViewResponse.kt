package com.dhc.dhcandroid.model

import kotlinx.serialization.Serializable

@Serializable
data class AnalysisViewResponse(
    val totalSavedMoney: String = "0",
    val weeklySavedMoney: String = "0",
    val generationMoneyViewResponse: GenerationMoneyViewResponse = GenerationMoneyViewResponse(),
)

@Serializable
data class GenerationMoneyViewResponse(
    val generation: Generation = Generation.UNKNOWN,
    val gender: Gender = Gender.MALE,
    val averageSpendMoney: String = "0",
)

@Serializable
enum class Generation {
    TEENAGERS, TWENTIES, THIRTIES, FORTIES, UNKNOWN,
}

@Serializable
data class CalendarDayMissionView(
    val day: Int = 0,
    val date: String = "",
    val finishedMissionCount: Int = 0,
    val totalMissionCount: Int = 0,
)
