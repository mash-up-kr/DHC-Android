package com.dhc.mypage

import com.dhc.dhcandroid.model.AnimalCard
import com.dhc.dhcandroid.model.MissionCategory
import com.dhc.presentation.mvi.UiEvent
import com.dhc.presentation.mvi.UiSideEffect
import com.dhc.presentation.mvi.UiState
import java.time.LocalDateTime

class MyPageContract {

    data class State(
        val sajuCard: AnimalCard = AnimalCard(),
        val birthDateTime: LocalDateTime? = null,
        val missionCategories: List<MissionCategory> = emptyList(),
    ) : UiState

    sealed interface Event : UiEvent

    sealed interface SideEffect : UiSideEffect
}
