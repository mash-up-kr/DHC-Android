package com.dhc.mypage

import androidx.lifecycle.viewModelScope
import com.dhc.common.getSuccessOrNull
import com.dhc.common.onException
import com.dhc.common.onFailure
import com.dhc.common.onSuccess
import com.dhc.dhcandroid.repository.AuthDataStoreRepository
import com.dhc.dhcandroid.repository.DhcRepository
import com.dhc.mypage.MyPageContract.Event
import com.dhc.mypage.MyPageContract.SideEffect
import com.dhc.mypage.MyPageContract.State
import com.dhc.mypage.model.MissionCategoryUiModel
import com.dhc.mypage.model.MyInfoUiModel
import com.dhc.presentation.mvi.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val authRepository: AuthDataStoreRepository,
    private val dhcRepository: DhcRepository,
) : BaseViewModel<State, Event, SideEffect>() {

    init {
        viewModelScope.launch {
            authRepository.getEncodedUserId().firstOrNull()?.let { userId ->
                reduce { copy(userId = userId) }
            }
        }
    }

    override fun createInitialState(): State {
        return State()
    }

    override suspend fun handleEvent(event: Event) {
        when (event) {
            is Event.ClickAppResetButton -> reduce { copy(isShowAppResetDialog = true) }
            is Event.ClickAppResetConfirmButton -> {
                coroutineScope {
                    authRepository.getUserId().firstOrNull()?.let {
                        dhcRepository.deleteUser(it)
                            .onSuccess {
                                authRepository.clearUserId()
                                dhcRepository.resetCachedCalendarView()
                                postSideEffect(SideEffect.NavigateToIntro)
                            }
                            .onFailure { _, _ -> postSideEffect(SideEffect.ShowToast("회원 탈퇴에 실패했습니다")) }
                            .onException { postSideEffect(SideEffect.ShowToast("회원 탈퇴에 실패했습니다")) }
                    } ?: run {
                        postSideEffect(SideEffect.ShowToast("회원 탈퇴에 실패했습니다"))
                    }
                    reduce { copy(isShowAppResetDialog = false) }
                }
            }

            is Event.ClickDialogDismissButton -> reduce { copy(isShowAppResetDialog = false) }
        }
    }

    fun loadMyPageData() = viewModelScope.launch {
        val userId = authRepository.getUserId().firstOrNull().orEmpty()
        dhcRepository.getMyPageView(userId)
            .onSuccess { response ->
                response ?: return@onSuccess
                reduce {
                    copy(
                        myInfo = MyInfoUiModel.from(myPageResponse = response),
                        missionCategories = response.preferredMissionCategoryList.map {
                            MissionCategoryUiModel.from(
                                it
                            )
                        },
                    )
                }
            }
    }
}
