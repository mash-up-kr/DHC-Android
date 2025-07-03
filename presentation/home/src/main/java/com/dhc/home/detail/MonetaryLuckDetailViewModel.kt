package com.dhc.home.detail

import androidx.lifecycle.viewModelScope
import com.dhc.common.getSuccessOrNull
import com.dhc.dhcandroid.repository.AuthDataStoreRepository
import com.dhc.dhcandroid.repository.DhcRepository
import com.dhc.home.detail.MoneyDetailLuckContract.Event
import com.dhc.home.detail.MoneyDetailLuckContract.SideEffect
import com.dhc.home.detail.MoneyDetailLuckContract.State
import com.dhc.home.detail.utils.toMonetaryLuckInfo
import com.dhc.presentation.mvi.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class MonetaryLuckDetailViewModel @Inject constructor(
    private val authRepository: AuthDataStoreRepository,
    private val dhcRepository: DhcRepository,
) : BaseViewModel<State, Event, SideEffect>() {
    override fun createInitialState(): State {
        return State()
    }

    override suspend fun handleEvent(event: Event) {}

    fun loadInitialData() = viewModelScope.launch {
        val userId = authRepository.getUserId().firstOrNull() ?: return@launch
        val data = dhcRepository.getDailyFortune(userId, LocalDate.now())
        reduce {
            copy(
                monetaryLuckInfo = data.getSuccessOrNull()
                    ?.toMonetaryLuckInfo()
                    ?: copy().monetaryLuckInfo
            )
        }
    }
}
