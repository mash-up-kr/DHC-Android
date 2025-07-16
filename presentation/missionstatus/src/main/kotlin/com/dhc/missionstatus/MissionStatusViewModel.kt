package com.dhc.missionstatus

import androidx.lifecycle.viewModelScope
import com.dhc.common.getSuccessOrNull
import com.dhc.common.onSuccess
import com.dhc.designsystem.calendar.model.DhcCalendarDayData
import com.dhc.designsystem.calendar.model.DhcCalendarMonthData
import com.dhc.dhcandroid.repository.AuthDataStoreRepository
import com.dhc.dhcandroid.repository.DhcRepository
import com.dhc.dhcandroid.repository.EasterEggRepository
import com.dhc.missionstatus.MissionStatusContract.Event
import com.dhc.missionstatus.MissionStatusContract.SideEffect
import com.dhc.missionstatus.MissionStatusContract.State
import com.dhc.missionstatus.ui.ConsumptionAnalysisUiModel
import com.dhc.missionstatus.ui.MissionAnalysisUiModel
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
    private val easterEggRepository: EasterEggRepository,
) : BaseViewModel<State, Event, SideEffect>() {
    override fun createInitialState(): State {
        return State()
    }

    override suspend fun handleEvent(event: Event) {
        when (event) {
            is Event.ClickCalendarDate -> {
                val birthDay = state.value.missionAnalysisUiModel?.easterEggBirthDay ?: return
                val clickedDate = event.date

                if (clickedDate.dayOfYear == birthDay.dayOfYear) {
                    val userId = authRepository.getUserId().firstOrNull() ?: return
                    dhcRepository.updateEasterEggHistory(userId)
                }
            }
        }
    }

    fun loadAnalysisUiData() = viewModelScope.launch {
        val userId = authRepository.getUserId().firstOrNull() ?: return@launch
        dhcRepository.getAnalysisView(userId)
            .onSuccess { data ->
                data ?: return@onSuccess // Todo :: null 응답 관련 처리 필요
                reduce { copy(consumptionAnalysisUiModel = ConsumptionAnalysisUiModel.from(data)) }
            }
    }

    suspend fun getCalendarData(yearMonth: LocalDate): Map<LocalDate, DhcCalendarMonthData> {
        if (yearMonth.isAfter(LocalDate.now())) {
            reduce {
                copy(
                    missionAnalysisUiModel = MissionAnalysisUiModel(
                        currentMonth = yearMonth.monthValue,
                        averageSucceedProbability = 0,
                    )
                )
            }
            return emptyMap()
        }

        val userId = authRepository.getUserId().firstOrNull() ?: return emptyMap()
        val result = dhcRepository.getCalendarView(userId, yearMonth).getSuccessOrNull()
        val easterEggBirthDay = easterEggRepository.getBirthDay()

        reduce {
            copy(
                missionAnalysisUiModel = MissionAnalysisUiModel
                    .from(result?.threeMonthViewResponse?.firstOrNull { it.month == yearMonth.monthValue })
                    ?.copy(easterEggBirthDay = easterEggBirthDay)
            )
        }

        return result?.threeMonthViewResponse
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
