package com.dhc.intro.category

import androidx.lifecycle.viewModelScope
import com.dhc.common.getSuccessOrNull
import com.dhc.dhcandroid.repository.AuthDataStoreRepository
import com.dhc.dhcandroid.repository.DhcRepository
import com.dhc.dhcandroid.repository.EasterEggRepository
import com.dhc.dhcandroid.repository.UserRepository
import com.dhc.presentation.mvi.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.dhc.intro.category.IntroCategoryContract.State
import com.dhc.intro.category.IntroCategoryContract.Event
import com.dhc.intro.category.IntroCategoryContract.SideEffect
import com.dhc.intro.category.model.CategoryItem
import kotlinx.coroutines.launch
import java.time.LocalDate

@HiltViewModel
class IntroCategoryViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val authDataStoreRepository: AuthDataStoreRepository,
    private val dhcRepository: DhcRepository,
    private val easterEggRepository: EasterEggRepository,
) : BaseViewModel<State, Event, SideEffect>() {

    init {
        viewModelScope.launch {
            loadData()
        }
    }

    private suspend fun loadData() {
        val categoryItems = dhcRepository.getMissionCategories().getSuccessOrNull()?.categories?.map {
            CategoryItem(
                name = it.name,
                displayName = it.displayName,
                imageUrl = it.image
            )
        }

        if (categoryItems == null) {
            reduce {
                copy(
                    loadState = IntroCategoryContract.LoadState.Error,
                )
            }
        } else {
            reduce {
                copy(
                    categoryItems = categoryItems,
                    loadState = IntroCategoryContract.LoadState.Success,
                )
            }
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
                userRepository.updateCategory(event.currentState.selectedCategoryItems.map { it.name })
                val userToken = authDataStoreRepository.getUUID().orEmpty()
                dhcRepository.registerUser(
                    userProfile = userRepository.getUserProfile().copy(userToken = userToken)
                ).getSuccessOrNull()?.id?.let { userId ->
                    authDataStoreRepository.setUserId(userId)
                    updateEasterEggBirthDay()
                }
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
            is Event.ClickRetryButton -> {
                reduce {
                    copy(
                        loadState = IntroCategoryContract.LoadState.Loading,
                    )
                }
                loadData()
            }
        }
    }

    private suspend fun updateEasterEggBirthDay() {
        val birthDayString = userRepository.getUserProfile().birthDate?.date ?: return
        val birthDay = LocalDate.parse(birthDayString)
        easterEggRepository.setBirthDay(birthDay)
    }
}
