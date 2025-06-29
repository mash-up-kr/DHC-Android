package com.dhc.missionstatus

import androidx.lifecycle.viewModelScope
import com.dhc.common.getSuccessOrNull
import com.dhc.common.onSuccess
import com.dhc.designsystem.calendar.model.DhcCalendarDayData
import com.dhc.designsystem.calendar.model.DhcCalendarMonthData
import com.dhc.dhcandroid.repository.AuthDataStoreRepository
import com.dhc.dhcandroid.repository.DhcRepository
import com.dhc.missionstatus.MissionStatusContract.Event
import com.dhc.missionstatus.MissionStatusContract.SideEffect
import com.dhc.missionstatus.MissionStatusContract.State
import com.dhc.missionstatus.ui.ConsumptionAnalysisUiModel
import com.dhc.presentation.mvi.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class MissionStatusViewModel @Inject constructor(
    private val authRepository: AuthDataStoreRepository,
    private val dhcRepository: DhcRepository,
) : BaseViewModel<State, Event, SideEffect>() {
    override fun createInitialState(): State {
        return State()
    }

    override suspend fun handleEvent(event: Event) {
        // Todo - 구현 필요
    }

    fun loadAnalysisUiData() = viewModelScope.launch {
        val userId = authRepository.getUUID().firstOrNull().orEmpty()
        dhcRepository.getAnalysisView(userId)
            .onSuccess { data ->
                data ?: return@onSuccess // Todo :: null 응답 관련 처리 필요
                reduce { copy(consumptionAnalysisUiModel = ConsumptionAnalysisUiModel.from(data)) }
            }
    }

    suspend fun getCalendarData(yearMonth: LocalDate): Map<LocalDate, DhcCalendarMonthData> {
        val userId = authRepository.getUUID().firstOrNull().orEmpty()
        return dhcRepository.getCalendarView(userId, yearMonth)
            .getSuccessOrNull()
            ?.threeMonthViewResponse
            ?.mapIndexed { index, data ->
                val month = yearMonth.plusMonths(index - 1L)
                month to DhcCalendarMonthData(
                    yearMonth = month,
                    data = data.calendarDayMissionViews.associate { dayMissionView ->
                        dayMissionView.day to DhcCalendarDayData(
                            finishedMissionCount = dayMissionView.finishedMissionCount,
                        )
                    }
                )
            }
            ?.toMap()
            .orEmpty()

    }
}
