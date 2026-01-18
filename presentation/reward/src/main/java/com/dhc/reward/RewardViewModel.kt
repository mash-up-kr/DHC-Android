package com.dhc.reward

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.dhc.common.onFailure
import com.dhc.common.onSuccess
import com.dhc.dhcandroid.repository.AuthDataStoreRepository
import com.dhc.dhcandroid.repository.DhcRepository
import com.dhc.presentation.mvi.BaseViewModel
import com.dhc.reward.model.RewardUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RewardViewModel @Inject constructor(
    private val authRepository: AuthDataStoreRepository,
    private val dhcRepository: DhcRepository,
) : BaseViewModel<RewardContract.State, RewardContract.Event, RewardContract.SideEffect>() {

    override fun createInitialState(): RewardContract.State {
        return RewardContract.State()
    }

    init {
        getRewardProgress()
    }

    override suspend fun handleEvent(event: RewardContract.Event) {
        when (event) {
            RewardContract.Event.ClickOpenRewardButton -> {
                // TODO: 리워드 오픈 로직
            }

            RewardContract.Event.ClickErrorRetryButton -> {
                getRewardProgress()
            }

            is RewardContract.Event.ClickRewardItem -> {
                // TODO: 리워드 아이템 클릭 처리
                postSideEffect(RewardContract.SideEffect.NavigateToYearFortune)
            }
        }
    }

    private fun getRewardProgress() {
        viewModelScope.launch {
            reduce { copy(rewardState = RewardContract.RewardState.Loading) }

            val userId = authRepository.getUserId().firstOrNull()
            if (userId != null) {
                dhcRepository.getRewardProgress(userId)
                    .onSuccess { response ->
                        response ?: return@onSuccess
                        val rewardUiModel = RewardUiModel.from(response)
                        reduce {
                            copy(
                                rewardInfo = rewardUiModel,
                                rewardState = RewardContract.RewardState.Success
                            )
                        }
                    }.onFailure { code, message ->
                        Log.d("getRewardProgress", "onFailure: code=$code, message=$message")
                        reduce { copy(rewardState = RewardContract.RewardState.Error) }
                    }
            } else {
                reduce { copy(rewardState = RewardContract.RewardState.Error) }
            }
        }
    }
}