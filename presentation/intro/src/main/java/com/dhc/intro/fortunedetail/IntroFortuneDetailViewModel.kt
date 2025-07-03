package com.dhc.intro.fortunedetail

import com.dhc.presentation.mvi.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.dhc.intro.fortunedetail.IntroFortuneDetailContract.State
import com.dhc.intro.fortunedetail.IntroFortuneDetailContract.Event
import com.dhc.intro.fortunedetail.IntroFortuneDetailContract.SideEffect
import com.dhc.presentation.ui.monetaryDetail.FortuneCard
import com.dhc.presentation.ui.monetaryDetail.MonetaryLuckInfo
import com.dhc.presentation.ui.monetaryDetail.ScoreInfo

@HiltViewModel
class IntroFortuneDetailViewModel @Inject constructor(
) : BaseViewModel<State, Event, SideEffect>() {

    override fun createInitialState(): State {
        return State(
            monetaryLuckInfo = MonetaryLuckInfo(
                scoreInfo = ScoreInfo(),
                fortuneCard = FortuneCard(),
                monetaryDetail = "",
                todayTips = listOf(),
            )
        )
    }

    override suspend fun handleEvent(event: Event) {
        when (event) {
            Event.ClickNextButton -> postSideEffect(SideEffect.NavigateToNextScreen)
        }
    }
}
