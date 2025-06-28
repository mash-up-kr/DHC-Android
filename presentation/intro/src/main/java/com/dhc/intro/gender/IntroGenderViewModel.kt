package com.dhc.intro.gender

import com.dhc.dhcandroid.repository.UserRepository
import com.dhc.presentation.mvi.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.dhc.intro.gender.IntroGenderContract.State
import com.dhc.intro.gender.IntroGenderContract.Event
import com.dhc.intro.gender.IntroGenderContract.SideEffect

@HiltViewModel
class IntroGenderViewModel @Inject constructor(
    private val repository: UserRepository,
) : BaseViewModel<State, Event, SideEffect>() {

    override fun createInitialState(): State {
        return State()
    }

    override suspend fun handleEvent(event: Event) {
        when (event) {
            is Event.ClickNextButton -> {
                event.currentState.gender?.toServerType()?.let { gender ->
                    repository.updateGender(gender)
                }
                postSideEffect(SideEffect.NavigateToNextScreen)
            }

            is Event.SelectGender -> reduce { copy(gender = event.gender) }
        }
    }
}
