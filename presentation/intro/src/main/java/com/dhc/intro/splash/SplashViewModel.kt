package com.dhc.intro.splash

import androidx.lifecycle.viewModelScope
import com.dhc.presentation.mvi.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.dhc.intro.splash.SplashContract.State
import com.dhc.intro.splash.SplashContract.Event
import com.dhc.intro.splash.SplashContract.SideEffect
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@HiltViewModel
class SplashViewModel @Inject constructor(
) : BaseViewModel<State, Event, SideEffect>() {

    init {
        waitSplashTimeAndFinished()
    }

    override fun createInitialState(): State {
        return State()
    }

    override suspend fun handleEvent(event: Event) {
        when (event) {
            Event.SplashFinished -> {
                reduce { copy(isSplashFinished = true) }
            }
        }
    }

    private fun waitSplashTimeAndFinished() {
        viewModelScope.launch {
            delay(SPLASH_TIME)
            handleEvent(Event.SplashFinished)
        }
    }

    companion object {
        private const val SPLASH_TIME = 1500L // 2 seconds
    }
}
