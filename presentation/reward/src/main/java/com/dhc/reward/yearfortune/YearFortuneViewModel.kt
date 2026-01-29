package com.dhc.reward.yearfortune

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.dhc.common.onFailure
import com.dhc.common.onSuccess
import com.dhc.dhcandroid.repository.AuthDataStoreRepository
import com.dhc.dhcandroid.repository.DhcRepository
import com.dhc.presentation.mvi.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class YearFortuneViewModel @Inject constructor(
    private val authRepository: AuthDataStoreRepository,
    private val dhcRepository: DhcRepository,
) : BaseViewModel<YearFortuneContract.State, YearFortuneContract.Event, YearFortuneContract.SideEffect>() {

    override fun createInitialState(): YearFortuneContract.State {
        return YearFortuneContract.State()
    }

    init {
        loadYearFortune()
    }

    override suspend fun handleEvent(event: YearFortuneContract.Event) {
        when (event) {
            YearFortuneContract.Event.ClickErrorRetryButton -> {
                loadYearFortune()
            }
            YearFortuneContract.Event.ClickBackButton -> {
                postSideEffect(YearFortuneContract.SideEffect.NavigateBack)
            }
        }
    }

    private fun loadYearFortune() {
        viewModelScope.launch {
            reduce { copy(yearFortuneState = YearFortuneContract.YearFortuneState.Loading) }

            val userId = authRepository.getUserId().firstOrNull()
            if (userId != null) {
                dhcRepository.getYearlyFortune(userId)
                    .onSuccess { response ->
                        response ?: return@onSuccess
                        val yearFortuneInfo = response.toYearFortuneInfo()
                        reduce {
                            copy(
                                yearFortuneInfo = yearFortuneInfo,
                                yearFortuneState = YearFortuneContract.YearFortuneState.Success
                            )
                        }
                    }.onFailure { code, message ->
                        Log.d("loadYearFortune", "onFailure: code=$code, message=$message")
                        reduce { copy(yearFortuneState = YearFortuneContract.YearFortuneState.Error) }
                    }
            } else {
                reduce { copy(yearFortuneState = YearFortuneContract.YearFortuneState.Error) }
            }
        }
    }
}
