package com.example.survey

import androidx.lifecycle.viewModelScope
import com.dhc.common.onSuccess
import com.dhc.dhcandroid.repository.AuthDataStoreRepository
import com.dhc.dhcandroid.repository.DhcRepository
import com.dhc.presentation.mvi.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SurveyViewModel @Inject constructor(
    private val authRepository: AuthDataStoreRepository,
    private val dhcRepository: DhcRepository,
) : BaseViewModel<SurveyContract.State, SurveyContract.Event, SurveyContract.SideEffect>() {

    init {
        fetchShareToken()
    }

    override fun createInitialState(): SurveyContract.State {
        return SurveyContract.State()
    }

    override suspend fun handleEvent(event: SurveyContract.Event) {
        // No events to handle currently
    }

    private fun fetchShareToken() {
        viewModelScope.launch {
            val userId = authRepository.getUserId().firstOrNull() ?: return@launch
            dhcRepository.createShareToken(userId)
                .onSuccess { response ->
                    reduce {
                        copy(
                            shareToken = response?.shareCode,
                            isLoading = false,
                        )
                    }
                }
        }
    }
}
