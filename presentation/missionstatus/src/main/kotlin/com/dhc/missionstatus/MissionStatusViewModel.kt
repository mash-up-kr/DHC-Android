package com.dhc.missionstatus

import androidx.lifecycle.viewModelScope
import com.dhc.common.onSuccess
import com.dhc.dhcandroid.repository.AuthDataStoreRepository
import com.dhc.dhcandroid.repository.DhcRepository
import com.dhc.missionstatus.MissionStatusContract.Event
import com.dhc.missionstatus.MissionStatusContract.SideEffect
import com.dhc.missionstatus.MissionStatusContract.State
import com.dhc.missionstatus.ui.ConsumptionAnalysisUiModel
import com.dhc.presentation.mvi.BaseViewModel
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

class MissionStatusViewModel @Inject constructor(
    private val authRepository: AuthDataStoreRepository,
    private val dhcRepository: DhcRepository,
) : BaseViewModel<State, Event, SideEffect>() {
    override fun createInitialState(): State {
        return State() // Todo - 변경 필요
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
}
