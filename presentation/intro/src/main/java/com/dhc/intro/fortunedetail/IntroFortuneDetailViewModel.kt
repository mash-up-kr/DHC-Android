package com.dhc.intro.fortunedetail

import com.dhc.common.CalendarUtil
import com.dhc.designsystem.tipcard.TipCardModel
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
                scoreInfo = ScoreInfo(
                    date = CalendarUtil.getCurrentDate().run {
                        "%d년 %d월 %d일".format(year, month.value, dayOfMonth) // Todo : 공통 Formmater 로 이동
                    },
                    score = 85,
                    description = "마음이 들뜨는 날이에요,\n한템포 쉬어가요."
                ),
                fortuneCard = FortuneCard(
                    title = "최고의 날",
                    message = "네잎클로버",
                ),
                monetaryDetail = "오늘은 지갑을 더 단단히 쥐고 계셔야겠어요.\n괜히 시선 가는 거 많고, 충동구매가 살작 걱정되는 날이에요.\n꼭 필요한 소비인지 한 번만 더 생각해보면,\n내일의 나에게 분명 고마워할 거에요.\n\n행운의 색인 연두색이 들어간 소품을 곁에 두면 조금 더 차분한 하루가 될지도 몰라요.",
                todayTips = listOf(
                    TipCardModel(
                        title = "오늘의 추천 메뉴",
                        icon = "",
                        color = "#D7E1EE",
                        cont = "카레"
                    ),
                    TipCardModel(
                        title = "행운의 색상",
                        icon = "",
                        color = "#23B169",
                        cont = "연두색"
                    ),
                    TipCardModel(
                        title = "피해야 할 음식",
                        icon = "",
                        color = "#D7E1EE",
                        cont = "치킨, 닭"
                    ),
                    TipCardModel(
                        title = "피해야 할 색상",
                        icon = "",
                        color = "#D7E1EE",
                        cont = "흰색"
                    ),
                ),
            )
        )
    }

    override suspend fun handleEvent(event: Event) {
        when (event) {
            Event.ClickNextButton -> postSideEffect(SideEffect.NavigateToNextScreen)
        }
    }
}
