package com.dhc.intro.splash

import com.dhc.presentation.mvi.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.dhc.intro.splash.SplashContract.State
import com.dhc.intro.splash.SplashContract.Event
import com.dhc.intro.splash.SplashContract.SideEffect
import kotlinx.coroutines.delay

@HiltViewModel
class SplashViewModel @Inject constructor(
) : BaseViewModel<State, Event, SideEffect>() {

    override fun createInitialState(): State {
        return State()
    }

    override suspend fun handleEvent(event: Event) {
        when (event) {
            Event.LottieAnimationFinished -> {
                delay(500L)
                reduce { copy(isSplashFinished = true) }
            }
        }
    }
}
