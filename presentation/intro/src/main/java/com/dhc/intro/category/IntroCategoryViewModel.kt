package com.dhc.intro.category

import androidx.lifecycle.viewModelScope
import com.dhc.common.getSuccessOrNull
import com.dhc.dhcandroid.repository.AuthDataStoreRepository
import com.dhc.dhcandroid.repository.DhcRepository
import com.dhc.dhcandroid.repository.UserRepository
import com.dhc.presentation.mvi.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.dhc.intro.category.IntroCategoryContract.State
import com.dhc.intro.category.IntroCategoryContract.Event
import com.dhc.intro.category.IntroCategoryContract.SideEffect
import com.dhc.intro.category.model.CategoryItem
import kotlinx.coroutines.launch

@HiltViewModel
class IntroCategoryViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val authDataStoreRepository: AuthDataStoreRepository,
    private val dhcRepository: DhcRepository,
) : BaseViewModel<State, Event, SideEffect>() {

    init {
        viewModelScope.launch {
            val categoryItems = dhcRepository.getMissionCategories().getSuccessOrNull()?.categories?.map {
                CategoryItem(
                    name = it.name,
                    displayName = it.displayName,
                    imageUrl = it.imageUrl
                )
            } ?: emptyList()

            reduce { copy(categoryItems = categoryItems) }
        }
    }

    override fun createInitialState(): State {
        return State(
            categoryItems = emptyList()
        )
    }

    override suspend fun handleEvent(event: Event) {
        when (event) {
            is Event.ClickNextButton -> {
                userRepository.updateCategory(event.currentState.categoryItems.map { it.name })
//                val userToken = authDataStoreRepository.getUserToken().firstOrNull().orEmpty()
                val userToken = "507f1f77bcf86cd799439011" // Todo : 임시코드
                val userId = dhcRepository.registerUser(
                    userProfile = userRepository.getUserProfile().copy(userToken = userToken)
                ).getSuccessOrNull()?.id.orEmpty()
                authDataStoreRepository.setUserId(userId)

                postSideEffect(SideEffect.NavigateToNextScreen)
            }
            is Event.ClickCategoryItem -> reduce {
                copy(
                    selectedIndexSet = if (selectedIndexSet.contains(event.selectedIndex)) {
                        selectedIndexSet - event.selectedIndex
                    } else {
                        selectedIndexSet + event.selectedIndex
                    }
                )
            }
        }
    }
}
