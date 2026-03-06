package com.dhc.reward

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntRect
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.window.PopupPositionProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.dhc.common.drawBalloonTail
import com.dhc.designsystem.DhcTheme
import com.dhc.designsystem.DhcTypoTokens
import com.dhc.designsystem.GradientColor.fortuneBorderGradientLow
import com.dhc.designsystem.GradientColor.fortuneGradientLow
import com.dhc.designsystem.LocalDhcColors
import com.dhc.designsystem.SurfaceColor
import com.dhc.designsystem.TransparentColor
import com.dhc.designsystem.button.DhcButton
import com.dhc.designsystem.button.model.DhcButtonSize
import com.dhc.designsystem.button.model.DhcButtonStyle
import com.dhc.designsystem.reward.RewardProgressBar
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RewardScreen(
    modifier: Modifier = Modifier,
    state: RewardContract.State = RewardContract.State(),
    onEvent: (RewardContract.Event) -> Unit = {},
    navigateToYearFortune: () -> Unit = {},
) {
    val colors = LocalDhcColors.current
    val scrollState = rememberScrollState()
    val tooltipState = rememberTooltipState(isPersistent = true)
    val coroutineScope = rememberCoroutineScope()
    val configuration = LocalConfiguration.current
    val density = LocalDensity.current
    val tooltipWidth = (configuration.screenWidthDp - 40).dp
    val centeredTooltipPositionProvider = remember(density) {
        object : PopupPositionProvider {
            override fun calculatePosition(
                anchorBounds: IntRect,
                windowSize: IntSize,
                layoutDirection: LayoutDirection,
                popupContentSize: IntSize,
            ): IntOffset {
                val x = (windowSize.width - popupContentSize.width) / 2
                val y = anchorBounds.top - popupContentSize.height - with(density) { 10.dp.roundToPx() }
                return IntOffset(x, y)
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(top = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(164.dp),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = state.rewardInfo.user.rewardImageUrl,
                contentDescription = "level_image",
                modifier = Modifier
                    .size(132.dp, 145.dp)
                    .align(Alignment.BottomCenter),
            )
        }

        Spacer(modifier = Modifier.height(25.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Lv 배지
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(999999.dp))
                    .background(TransparentColor.glassEffect)
                    .padding(horizontal = 12.dp, vertical = 4.dp)
            ) {
                Text(
                    text = state.rewardInfo.user.rewardLevel,
                    style = DhcTypoTokens.TitleH8,
                    color = colors.text.textBodyPrimary
                )
            }

            Spacer(modifier = Modifier.size(8.dp))

            Text(
                text = buildAnnotatedString {
                    withStyle(
                        SpanStyle(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    colors.text.textHighLightsSecondary,
                                    colors.text.textMain
                                ),
                                startY = 0f,
                                endY = 100f
                            )
                        )
                    ) {
                        append(state.rewardInfo.user.rewardLevel)
                    }
                },
                style = DhcTypoTokens.TitleH1
            )

            Spacer(modifier = Modifier.size(8.dp))

            // 정보 아이콘
            TooltipBox(
                positionProvider = centeredTooltipPositionProvider,
                tooltip = {
                    Text(
                        modifier = Modifier
                            .width(tooltipWidth)
                            .drawBalloonTail(
                                brush = fortuneBorderGradientLow,
                                cornerWidth = 12.dp,
                                cornerHeight = 7.dp,
                            )
                            .background(
                                brush = fortuneBorderGradientLow,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(horizontal = 10.dp, vertical = 10.dp),
                        text = stringResource(R.string.reward_level_tooltip),
                        color = colors.text.textHighLightsPrimary,
                        style = DhcTypoTokens.Body5,
                        textAlign = TextAlign.Center,
                    )
                },
                state = tooltipState,
            ) {
                Image(
                    modifier = Modifier.clickable {
                        coroutineScope.launch {
                            tooltipState.show()
                        }
                    },
                    painter = painterResource(com.dhc.designsystem.R.drawable.ico_info_circle),
                    contentDescription = "information",
                    colorFilter = ColorFilter.tint(SurfaceColor.neutral400)
                )
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val hasUnusedReward = state.rewardInfo.rewardList.any { !it.isUsed }

            RewardCard(
                totalPoints = state.rewardInfo.user.totalPoint,
                currentLevelPoints = state.rewardInfo.user.currentLevelPoint,
                nextLevelRequiredPoints = state.rewardInfo.user.nextLevelRequiredPoint,
                rewardLevel = state.rewardInfo.user.rewardLevel,
                hasUnusedReward = hasUnusedReward,
                onClickOpenReward = {
                    onEvent(RewardContract.Event.ClickOpenRewardButton)
                },
                onClickRewardExplainButton = {
                    onEvent(RewardContract.Event.ClickRewardExplainButton)
                }
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        // 받은 리워드들 섹션
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(SurfaceColor.neutral700)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // 섹션 타이틀
            Text(
                text = stringResource(R.string.reward_received_rewards_title),
                style = DhcTypoTokens.TitleH6,
                color = colors.text.textMain
            )

            // 리워드 리스트
            ReceivedRewardsList(
                rewards = state.rewardInfo.rewardList.map {
                    ReceivedReward(
                        id = it.id.toString(),
                        name = it.title,
                        isUnlocked = it.isUnlocked,
                        isUsed = it.isUsed,
                        message = it.message,
                    )
                },
                onClickItem = { reward ->
                    onEvent(RewardContract.Event.ClickRewardItem(reward.id.toIntOrNull() ?: 0))
                    navigateToYearFortune()
                },
                onClickLockedItem = { reward ->
                    onEvent(RewardContract.Event.ClickLockedRewardItem(reward.message))
                }
            )
        }

        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Composable
private fun RewardCard(
    totalPoints: Int = 0,
    currentLevelPoints: Int = 0,
    nextLevelRequiredPoints: Int = 400,
    rewardLevel: String = "LV1",
    hasUnusedReward: Boolean = false,
    onClickOpenReward: () -> Unit = {},
    onClickRewardExplainButton: () -> Unit = {},
) {
    val colors = LocalDhcColors.current

    val currentLevelNumber = rewardLevel.removePrefix("LV").toIntOrNull() ?: 1
    Log.d(TAG, "RewardCard: $currentLevelNumber")
    val isMaxLevel = currentLevelNumber >= 8
    val canOpenReward = isMaxLevel && hasUnusedReward
    val remainingPoints = if (isMaxLevel) 0 else nextLevelRequiredPoints - currentLevelPoints

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(SurfaceColor.neutral700)
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                text = stringResource(R.string.reward_earned),
                style = DhcTypoTokens.TitleH7,
                color = SurfaceColor.neutral300
            )

            Row(
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = totalPoints.toString(),
                    style = DhcTypoTokens.TitleH1,
                    color = colors.text.textMain
                )
                Text(
                    text = "pt",
                    style = DhcTypoTokens.Body1,
                    color = SurfaceColor.neutral300
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(TransparentColor.glassEffect)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 툴팁 박스
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(
                        brush = fortuneBorderGradientLow
                    )
                    .padding(horizontal = 10.dp, vertical = 10.dp)
            ) {
                Text(
                    text = if (isMaxLevel) {
                        "Goal에 도달했어요!"
                    } else {
                        stringResource(R.string.reward_next_level, remainingPoints)
                    },
                    style = DhcTypoTokens.Body5,
                    color = colors.text.textHighLightsPrimary,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    maxLines = 1
                )
            }

            // 삼각형 화살표 (가운데 고정)
            Box(
                modifier = Modifier
                    .size(12.dp, 8.dp)
                    .offset(y = (-1).dp)
                    .drawBehind {
                        val trianglePath = Path().apply {
                            moveTo(size.width / 2, size.height)
                            lineTo(0f, 0f)
                            lineTo(size.width, 0f)
                            close()
                        }

                        drawPath(
                            path = trianglePath,
                            brush = fortuneBorderGradientLow
                        )
                    }
            )
        }

        // 프로그레스 바
        RewardProgressBar(
            currentStep = currentLevelNumber - 1,
            modifier = Modifier.fillMaxWidth(),
            totalStepList = listOf("1", "2", "3", "4", "5", "6", "7", "8")
        )
        DhcButton(
            text = stringResource(R.string.reward_open_reward_button),
            buttonSize = DhcButtonSize.LARGE,
            buttonStyle = DhcButtonStyle.Primary(canOpenReward),
            onClick = onClickOpenReward,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onClickRewardExplainButton()
                },
            text = stringResource(R.string.reward_explain_text_button),
            style = DhcTypoTokens.Body5,
            textDecoration = TextDecoration.Underline,
            color = colors.text.textHighLightsSecondary,
            textAlign = TextAlign.Center,
        )

    }
}

// 받은 리워드 데이터 모델
private data class ReceivedReward(
    val id: String,
    val name: String,
    val isUnlocked: Boolean = false,
    val isUsed: Boolean = false,
    val message: String = "",
)

@Composable
private fun ReceivedRewardsList(
    rewards: List<ReceivedReward> = listOf(
        ReceivedReward("1", "1년 운세"),
        ReceivedReward("2", "전반적 사주"),
        ReceivedReward("3", "복합 사주"),
        ReceivedReward("4", "복합 사주"),
        ReceivedReward("5", "1년 운세"),
        ReceivedReward("6", "전반적 사주"),
        ReceivedReward("7", "복합 사주"),
        ReceivedReward("8", "복합 사주"),
        ReceivedReward("9", "1년 운세"),
        ReceivedReward("10", "전반적 사주"),
        ReceivedReward("11", "복합 사주"),
    ),
    onClickItem: (ReceivedReward) -> Unit,
    onClickLockedItem: (ReceivedReward) -> Unit = {},
) {
    val itemsPerRow = 4
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
    ) {
        rewards.chunked(itemsPerRow).forEach { rowItems ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                rowItems.forEach { reward ->
                    ReceivedRewardItem(
                        reward = reward,
                        modifier = Modifier.weight(1f),
                        onClickItem = { onClickItem(reward) },
                        onClickLockedItem = { onClickLockedItem(reward) }
                    )
                }
                // 마지막 행에서 빈 공간 채우기
                repeat(itemsPerRow - rowItems.size) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
private fun ReceivedRewardItem(
    reward: ReceivedReward,
    onClickItem: () -> Unit,
    onClickLockedItem: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val colors = LocalDhcColors.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .clickable {
                if (reward.isUnlocked && reward.isUsed) onClickItem()
                else onClickLockedItem()
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 카드 (아이콘만 포함)
        Box(
            modifier = Modifier
                .size(52.dp)
                .aspectRatio(1f)
                .clip(RoundedCornerShape(8.dp))
                .background(colors.background.backgroundGlassEffect)
                .padding(10.dp),
            contentAlignment = Alignment.Center
        ) {
            if(reward.isUnlocked && reward.isUsed){
                Image(
                    painter = painterResource(com.dhc.designsystem.R.drawable.ico_gold_medal),
                    contentDescription = "lock icon",
                    modifier = Modifier.size(28.dp)
                )
            } else {
                Image(
                    painter = painterResource(com.dhc.designsystem.R.drawable.ico_lock),
                    contentDescription = "lock icon",
                    modifier = Modifier.size(28.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = reward.name,
            style = DhcTypoTokens.Body6,
            color = colors.text.textBodyPrimary,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(name = "리워드 화면 - 전체", showBackground = true, backgroundColor = 0xFF0F1114)
@Composable
private fun RewardScreenPreview() {
    DhcTheme {
        RewardScreen()
    }
}

@Preview(name = "레벨 1 - 50pt", showBackground = true, backgroundColor = 0xFF0F1114)
@Composable
private fun RewardCardLevel1Preview() {
    DhcTheme {
        RewardCard(
            totalPoints = 50,
            currentLevelPoints = 50,
            nextLevelRequiredPoints = 100,
            rewardLevel = "LV1",
            hasUnusedReward = false
        )
    }
}

@Preview(name = "레벨 3 - 300pt", showBackground = true, backgroundColor = 0xFF0F1114)
@Composable
private fun RewardCardLevel3Preview() {
    DhcTheme {
        RewardCard(
            totalPoints = 300,
            currentLevelPoints = 100,
            nextLevelRequiredPoints = 200,
            rewardLevel = "LV3",
            hasUnusedReward = false
        )
    }
}

@Preview(name = "레벨 5 - 700pt", showBackground = true, backgroundColor = 0xFF0F1114)
@Composable
private fun RewardCardLevel5Preview() {
    DhcTheme {
        RewardCard(
            totalPoints = 700,
            currentLevelPoints = 100,
            nextLevelRequiredPoints = 300,
            rewardLevel = "LV5",
            hasUnusedReward = false
        )
    }
}

@Preview(name = "레벨 8 - Goal 2000pt", showBackground = true, backgroundColor = 0xFF0F1114)
@Composable
private fun RewardCardLevel8Preview() {
    DhcTheme {
        RewardCard(
            totalPoints = 2000,
            currentLevelPoints = 400,
            nextLevelRequiredPoints = 400,
            rewardLevel = "LV8",
            hasUnusedReward = true
        )
    }
}

@Preview(name = "받은 리워드들", showBackground = true, backgroundColor = 0xFF0F1114)
@Composable
private fun ReceivedRewardsListPreview() {
    DhcTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = stringResource(R.string.reward_received_rewards_title),
                style = DhcTypoTokens.TitleH6,
                color = LocalDhcColors.current.text.textMain,
                modifier = Modifier.padding(horizontal = 4.dp)
            )
            ReceivedRewardsList(
                onClickItem = {}
            )
        }
    }
}
