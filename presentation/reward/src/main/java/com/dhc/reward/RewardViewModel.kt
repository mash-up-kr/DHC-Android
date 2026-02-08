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
                unlockYearlyFortune()
            }

            RewardContract.Event.ClickErrorRetryButton -> {
                getRewardProgress()
            }

            is RewardContract.Event.ClickRewardItem -> {
                postSideEffect(RewardContract.SideEffect.NavigateToYearFortune(isSampleData = false))
            }

            RewardContract.Event.ClickRewardExplainButton -> {
                postSideEffect(RewardContract.SideEffect.NavigateToYearFortune(isSampleData = true))
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

    private fun unlockYearlyFortune() {
        viewModelScope.launch {
            val hasUnusedReward = state.value.rewardInfo.rewardList.any { !it.isUsed }
            if (!hasUnusedReward) {
                postSideEffect(RewardContract.SideEffect.ShowToast("이미 모든 리워드가 사용되었습니다."))
                return@launch
            }

            val userId = authRepository.getUserId().firstOrNull()
            if (userId != null) {
                dhcRepository.unlockYearlyFortune(userId)
                    .onSuccess {
                        postSideEffect(RewardContract.SideEffect.ShowToast("리워드가 활성화되었습니다!"))
                        getRewardProgress()
                    }.onFailure { code, message ->
                        Log.d("unlockYearlyFortune", "onFailure: code=$code, message=$message")
                        postSideEffect(RewardContract.SideEffect.ShowToast("리워드 활성화에 실패했습니다."))
                    }
            } else {
                postSideEffect(RewardContract.SideEffect.ShowToast("사용자 정보를 찾을 수 없습니다."))
            }
        }
    }
}