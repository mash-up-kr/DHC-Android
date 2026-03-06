package com.dhc.reward.yearfortune

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.dhc.common.ImageResource
import com.dhc.common.onFailure
import com.dhc.common.onSuccess
import com.dhc.designsystem.R
import com.dhc.dhcandroid.repository.AuthDataStoreRepository
import com.dhc.dhcandroid.repository.DhcRepository
import com.dhc.presentation.mvi.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class YearFortuneViewModel @Inject constructor(
    private val authRepository: AuthDataStoreRepository,
    private val dhcRepository: DhcRepository,
    savedStateHandle: SavedStateHandle,
) : BaseViewModel<YearFortuneContract.State, YearFortuneContract.Event, YearFortuneContract.SideEffect>() {

    override fun createInitialState(): YearFortuneContract.State {
        return YearFortuneContract.State()
    }

    init {
        val isSampleData = savedStateHandle.get<Boolean>("isSampleData") ?: false
        if (isSampleData) {
            loadSampleData()
        } else {
            loadYearFortune()
        }
    }

    override suspend fun handleEvent(event: YearFortuneContract.Event) {
        when (event) {
            YearFortuneContract.Event.ClickErrorRetryButton -> {
                loadYearFortune()
            }
            YearFortuneContract.Event.ClickBackButton -> {
                postSideEffect(YearFortuneContract.SideEffect.NavigateBack)
            }
        }
    }

    private fun loadYearFortune() {
        viewModelScope.launch {
            reduce { copy(yearFortuneState = YearFortuneContract.YearFortuneState.Loading) }

            val userId = authRepository.getUserId().firstOrNull()
            if (userId != null) {
                dhcRepository.getYearlyFortune(userId)
                    .onSuccess { response ->
                        response ?: return@onSuccess
                        val yearFortuneInfo = response.toYearFortuneInfo()
                        reduce {
                            copy(
                                yearFortuneInfo = yearFortuneInfo,
                                yearFortuneState = YearFortuneContract.YearFortuneState.Success
                            )
                        }
                    }.onFailure { code, message ->
                        Log.d("loadYearFortune", "onFailure: code=$code, message=$message")
                        reduce { copy(yearFortuneState = YearFortuneContract.YearFortuneState.Error) }
                    }
            } else {
                reduce { copy(yearFortuneState = YearFortuneContract.YearFortuneState.Error) }
            }
        }
    }

    private fun loadSampleData() {
        viewModelScope.launch {
            reduce { copy(yearFortuneState = YearFortuneContract.YearFortuneState.Loading) }

            val sampleYearFortuneInfo = createSampleYearFortuneInfo()
            reduce {
                copy(
                    yearFortuneInfo = sampleYearFortuneInfo,
                    yearFortuneState = YearFortuneContract.YearFortuneState.Success,
                    isSampleData = true,
                )
            }
        }
    }

    private fun createSampleYearFortuneInfo(): YearFortuneInfo {
        return YearFortuneInfo(
            scoreInfo = com.dhc.presentation.ui.monetaryDetail.ScoreInfo(
                date = "2026년 운세 총평",
                score = 65,
                description = "2026년, 뜨거운 열정 속에서 새로운 기회를 발견할 한 해!"
            ),
            fortuneCard = com.dhc.presentation.ui.monetaryDetail.FortuneCard(
                title = "설레는 원숭이",
                message = "기대되는 날",
                image = ImageResource.Drawable(R.drawable.fortune_card_sample)
            ),
            overallFortune = "2026년은 당신의 잠재된 열정과 추진력이 폭발하는 시기가 될 것입니다. 강한 화기운이 당신을 이끌어 새로운 도전을 두려워하지 않게 하지만, 때로는 과도한 자신감이 독이 될 수 있으니 주의가 필요합니다. 주변과의 조화를 중요시하며 균형을 잃지 않도록 노력한다면, 많은 성취를 이룰 수 있는 한 해가 될 것입니다.",
            quickViewFortune = listOf(
                QuickViewFortuneItem(
                    title = "금전운",
                    content = "수입을 늘릴 수 있는 좋은 기회가 찾아오지만, 충동적인 지출이나 과도한 투자는 피하는 것이 좋습니다.",
                    ImageResource.Drawable(R.drawable.ico_money_pocket)
                ),
                QuickViewFortuneItem(
                    title = "연애운",
                    content = "솔로라면 새로운 인연을 만날 기회가 많아지며, 현재 연인과의 관계에서는 더욱 깊은 유대감을 형성할 수 있습니다.",
                    ImageResource.Drawable(R.drawable.ico_heart_target)
                ),
                QuickViewFortuneItem(
                    title = "학업운",
                    content = "강한 의욕과 집중력으로 학업에서 좋은 성과를 기대할 수 있습니다. 새로운 분야에 대한 호기심이 커져 배움의 즐거움을 느낄 것입니다.",
                    ImageResource.Drawable(R.drawable.ico_study)
                )
            ),
            fiveElementData = listOf(
                FiveElementData.fromPercentage(FiveElementType.WOOD, 20),
                FiveElementData.fromPercentage(FiveElementType.FIRE, 35),
                FiveElementData.fromPercentage(FiveElementType.EARTH, 20),
                FiveElementData.fromPercentage(FiveElementType.METAL, 10),
                FiveElementData.fromPercentage(FiveElementType.WATER, 15)
            ),
            energyChange = "화의 기운은 '결단력·집중·주체성'을 밝히는 에너지에요\n\n2026년은 병오(丙午)년으로, 강한 화(火)의 기운이 지배적인 한 해입니다. 이는 당신의 내면에 숨겨진 열정과 주체성을 폭발시키는 에너지로 작용할 것입니다. 새로운 아이디어를 떠올리고, 과감하게 실행에 옮길 수 있는 추진력을 얻게 될 것입니다.",
            yearTips = listOf(
                com.dhc.designsystem.tipcard.TipCardModel(
                    title = "행운의 메뉴",
                    icon = ImageResource.Drawable(R.drawable.ico_knife),
                    color = null,
                    cont = "해산물 요리 (수기운 보충)"
                ),
                com.dhc.designsystem.tipcard.TipCardModel(
                    title = "행운의 색상",
                    icon = ImageResource.Drawable(R.drawable.ico_clover),
                    color = "#000000",
                    cont = "검정색"
                ),
                com.dhc.designsystem.tipcard.TipCardModel(
                    title = "피해야 할 색상",
                    icon = ImageResource.Drawable(R.drawable.ico_red_face),
                    color = "#FF0000",
                    cont = "빨간색"
                ),
                com.dhc.designsystem.tipcard.TipCardModel(
                    title = "피해야 할 음식",
                    icon = ImageResource.Drawable(R.drawable.ico_green_face),
                    color = null,
                    cont = "매운 음식 (화기운 강화)"
                )
            )
        )
    }
}
