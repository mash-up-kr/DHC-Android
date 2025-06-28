package com.dhc.intro.birthtime

import com.dhc.designsystem.spinner.model.TimeType
import com.dhc.presentation.mvi.UiEvent
import com.dhc.presentation.mvi.UiSideEffect
import com.dhc.presentation.mvi.UiState

class IntroBirthTimeContract {

    data class State(
        val isIdkChecked: Boolean = false,
        val timeType: TimeType = TimeType.AM,
        val time: Int = 1,
        val minute: Int = 0,
    ) : UiState {
        private val timeFormat = "%02d:%02d" // Todo : 공통 Formatter 로 이동
        val birthTime = timeFormat.format(time, minute) // Todo : 어떤식으로 보내는지 확인 후 수정
    }

    sealed interface Event : UiEvent {
        data class ClickNextButton(val currentState: State) : Event
        data class SelectIdkButton(val isIdkEnabled: Boolean) : Event
        data class SelectBirthTime(val timeType: TimeType, val time: Int, val minute: Int) : Event
    }

    sealed interface SideEffect : UiSideEffect {
        data object NavigateToNextScreen : SideEffect
    }
}
