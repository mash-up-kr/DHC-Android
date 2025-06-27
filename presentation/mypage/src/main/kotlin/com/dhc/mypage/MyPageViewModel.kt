package com.dhc.mypage

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.dhc.common.onFailure
import com.dhc.common.onSuccess
import com.dhc.dhcandroid.repository.DhcRepository
import com.dhc.dhcandroid.repository.UserDataStoreRepository
import com.dhc.mypage.MyPageContract.Event
import com.dhc.mypage.MyPageContract.SideEffect
import com.dhc.mypage.MyPageContract.State
import com.dhc.mypage.model.MyInfoUiModel
import com.dhc.presentation.mvi.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val userRepository: UserDataStoreRepository,
    private val dhcRepository: DhcRepository,
    private val savedStateHandle: SavedStateHandle,
) : BaseViewModel<State, Event, SideEffect>() {
    override fun createInitialState(): State {
        return State()
    }

    override suspend fun handleEvent(event: Event) {
        TODO("Not yet implemented")
    }

    fun loadMyPageData() = viewModelScope.launch {
        val userId = userRepository.getUUID()
        dhcRepository.getMyPageView("685d5c51e2653443ce6cf530") // Todo :: 추후 userId로 변경 필요
            .onSuccess { response ->
                response ?: return@onSuccess
                reduce {
                    copy(
                        myInfo = MyInfoUiModel.from(myPageResponse = response),
                        missionCategories = response.preferredMissionCategoryList,
                    )
                }
            }
            .onFailure { _, message ->
                // Todo :: 실패 처리
            }
    }


}
