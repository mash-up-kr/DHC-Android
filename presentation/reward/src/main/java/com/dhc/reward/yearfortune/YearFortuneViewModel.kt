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
                    yearFortuneState = YearFortuneContract.YearFortuneState.Success
                )
            }
        }
    }

    private fun createSampleYearFortuneInfo(): YearFortuneInfo {
        return YearFortuneInfo(
            scoreInfo = com.dhc.presentation.ui.monetaryDetail.ScoreInfo(
                date = "2025년 5월 20일",
                score = 79,
                description = "올 한해는 전반적으로 마음이 들뜨는 날이에요, \n한템포 쉬어가요."
            ),
            fortuneCard = com.dhc.presentation.ui.monetaryDetail.FortuneCard(
                title = "",
                message = "",
                image = ImageResource.Drawable(R.drawable.fortune_card_sample)
            ),
            overallFortune = "이번 달은 마음이 한층 단단해지는 시기예요.\n불필요한 걱정에 에너지를 쓰기보단, 지금 눈앞의 상황에 집중하면 일이 자연스럽게 풀려갑니다.\n충동적인 선택이 아니라 조금 더 생각하고 결정하는 것만으로도 내일의 나에게 분명 고마운 한 달이 될 거예요.",
            quickViewFortune = listOf(
                QuickViewFortuneItem(
                    title = "금전운",
                    content = "현재의 상황을 최우선적으로 고려해보는 것이 좋아요. 과거의 상황까지 고려할 필요는 없어요.",
                    ImageResource.Drawable(R.drawable.ico_money_pocket)
                ),
                QuickViewFortuneItem(
                    title = "연애운",
                    content = "상대방의 말보다 분위기를 읽는 게 관계에 좋은 영향이 있어요.",
                    ImageResource.Drawable(R.drawable.ico_heart_target)
                ),
                QuickViewFortuneItem(
                    title = "학업운",
                    content = "현재의 상황을 최우선적으로 고려해보는 것이 좋아요. 과거의 상황까지 고려할 필요는 없어요.",
                    ImageResource.Drawable(R.drawable.ico_study)
                )
            ),
            fiveElementData = listOf(
                FiveElementData.fromPercentage(FiveElementType.WOOD, 20),
                FiveElementData.fromPercentage(FiveElementType.FIRE, 40),
                FiveElementData.fromPercentage(FiveElementType.EARTH, 30),
                FiveElementData.fromPercentage(FiveElementType.METAL, 20),
                FiveElementData.fromPercentage(FiveElementType.WATER, 10)
            ),
            energyChange = "화의 기운은\n'결단력・집중・주체성'을 밝히는 에너지예요.\n불안이나 충동으로 흐르면 지치기 쉽지만,\n올바르게 쓰이면 원하는 방향으로 크게 나아가는 달이 됩니다.\n\n그래서 이번 달엔…\n• 지금의 상황을 기준으로 결정해보세요.\n• 감정보다는 리듬을 안정시키면 잘 흘러가요.\n• 내일의 나에게 분명 고마운 선택을 하게 될 거예요.",
            yearTips = listOf(
                com.dhc.designsystem.tipcard.TipCardModel(
                    title = "추천메뉴",
                    icon = ImageResource.Drawable(R.drawable.ico_knife),
                    color = null,
                    cont = "카레"
                ),
                com.dhc.designsystem.tipcard.TipCardModel(
                    title = "피해야 할 음식",
                    icon = ImageResource.Drawable(R.drawable.ico_green_face),
                    color = null,
                    cont = "치킨, 닭"
                ),
                com.dhc.designsystem.tipcard.TipCardModel(
                    title = "행운의 색상",
                    icon = ImageResource.Drawable(R.drawable.ico_clover),
                    color = "#23B169",
                    cont = "연두색"
                ),
                com.dhc.designsystem.tipcard.TipCardModel(
                    title = "피해야 할 색상",
                    icon = ImageResource.Drawable(R.drawable.ico_red_face),
                    color = "#F4F4F5",
                    cont = "흰색"
                )
            )
        )
    }
}
