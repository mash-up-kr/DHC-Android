package com.dhc.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onRoot
import com.dhc.designsystem.DhcTheme
import com.dhc.designsystem.SurfaceColor
import com.dhc.home.main.HomeContract
import com.dhc.home.main.HomeScreen
import com.dhc.home.model.HomeUiModel
import com.dhc.home.model.MissionUiModel
import com.dhc.home.model.RewardEvent
import com.dhc.home.model.TodayDailyFortuneUiModel
import com.dhc.dhcandroid.model.MissionType
import com.github.takahirom.roborazzi.captureRoboImage
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [33], qualifiers = "w411dp-h891dp-xhdpi")
class HomeScreenRoborazziTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun homeScreen_default() {
        composeTestRule.setContent {
            DhcTheme {
                HomeScreen(
                    state = HomeContract.State(
                        homeState = HomeContract.HomeState.Success,
                        homeInfo = HomeUiModel(
                            todayDailyFortune = TodayDailyFortuneUiModel(
                                date = "5월 20일",
                                fortuneTitle = "지갑이 들뜨는 날,\n한템포 쉬어요.",
                                score = 35,
                                fortuneCardTitle = "최고의 날",
                                fortuneCardSubTitle = "네잎클로버",
                            ),
                            longTermMission = MissionUiModel(
                                missionId = "1",
                                title = "텀블러 들고 다니기",
                                category = "소비습관",
                                type = MissionType.LONG_TERM,
                                endDate = "D-12",
                                difficulty = "Easy",
                            ),
                            todayDailyMissionList = listOf(
                                MissionUiModel(
                                    missionId = "2",
                                    title = "가까운 거리 걸어다니기",
                                    category = "식음료",
                                    type = MissionType.DAILY,
                                    difficulty = "Easy",
                                    isChecked = true,
                                ),
                                MissionUiModel(
                                    missionId = "3",
                                    title = "간식비에 3000원 이상 금지",
                                    category = "식음료",
                                    type = MissionType.DAILY,
                                    difficulty = "Easy",
                                    isChecked = true,
                                ),
                            ),
                            rewardEvent = RewardEvent(
                                rewardEventTitle = "오늘의 미션",
                                rewardEventSubtitle = "단 3개만 도전해 보세요",
                                rewardCompletedCount = 1,
                                rewardTotalCount = 3,
                            ),
                        ),
                        missionTimerText = "20 : 08 : 50",
                    ),
                    eventHandler = {},
                )
            }
        }

        composeTestRule.onRoot().captureRoboImage("HomeScreen_default.png")
    }
}
