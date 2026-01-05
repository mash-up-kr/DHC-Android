package com.dhc.reward

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import com.dhc.designsystem.DhcTheme
import com.dhc.designsystem.DhcTypoTokens
import com.dhc.designsystem.LocalDhcColors
import com.dhc.designsystem.SurfaceColor
import com.dhc.designsystem.TransparentColor
import com.dhc.designsystem.button.DhcButton
import com.dhc.designsystem.button.model.DhcButtonSize
import com.dhc.designsystem.button.model.DhcButtonStyle
import com.dhc.designsystem.reward.RewardProgressBar
import com.dhc.designsystem.tooltip.DhcTooltip
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RewardScreen(
    modifier: Modifier = Modifier,
) {
    val colors = LocalDhcColors.current
    val scrollState = rememberScrollState()
    val tooltipState = rememberTooltipState(isPersistent = true)
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(top = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // 그래픽 영역 (변경예정)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(164.dp)
                .background(SurfaceColor.neutral800),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(R.string.reward_graphic_placeholder),
                color = colors.text.textMain.copy(alpha = 0.4f),
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(25.dp))

        // Lv.3 동 복주머니 타이틀
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
                    text = stringResource(R.string.reward_level_badge, 3),
                    style = DhcTypoTokens.TitleH8,
                    color = colors.text.textBodyPrimary
                )
            }

            Spacer(modifier = Modifier.size(8.dp))

            // 동 복주머니 타이틀
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
                        append(stringResource(R.string.reward_lucky_bag))
                    }
                },
                style = DhcTypoTokens.TitleH1
            )

            Spacer(modifier = Modifier.size(8.dp))

            // 정보 아이콘
            DhcTooltip(
                tooltipState = tooltipState,
                tooltipText = "tooltip test",
                content = {
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
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        // 리워드 카드들
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // 리워드 카드
            RewardCard()

            // 프리미엄 운세 카드
            PremiumFortuneCard()
        }

        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Composable
private fun RewardCard(
    currentPoints: Int = 400,
    currentStep: Int = 1,
) {
    val colors = LocalDhcColors.current
    
    // 레벨별 필요 포인트 (예시)
    val levelThresholds = listOf(0, 100, 500, 900, 1600)
    val nextLevelPoints = levelThresholds.getOrNull(currentStep + 1) ?: levelThresholds.last()
    val remainingPoints = nextLevelPoints - currentPoints
    
    // 툴팁 위치 계산 (0.0 ~ 1.0)
    val progressWidthByStep = listOf(0.094f, 0.37f, 0.66f, 1.0f)
    val currentProgress = progressWidthByStep.getOrElse(currentStep) { 0.094f }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(SurfaceColor.neutral700)
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // 지금까지 얻은 리워드
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
                    text = currentPoints.toString(),
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

        // 구분선
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(TransparentColor.glassEffect)
        )

        // 툴팁 (동적 위치) - 모든 레벨에서 표시
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            // 툴팁의 삼각형이 가리킬 X 위치 계산 (프로그레스 바의 실제 너비 고려)
            val progressBarWidth = 298.dp // 실제 프로그레스 바 너비 (fillMaxWidth - padding 20dp * 2)
            val tooltipWidth = 200.dp // 툴팁 고정 너비
            val targetX = currentProgress * progressBarWidth
            val tooltipX = (targetX - (tooltipWidth / 2))
                .coerceIn(0.dp, progressBarWidth - tooltipWidth)
            
            Column(
                modifier = Modifier
                    .size(width = tooltipWidth, height = 48.dp)
                    .align(Alignment.TopStart)
                    .offset(x = tooltipX),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // 툴팁 박스
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    SurfaceColor.neutral30,
                                    colors.text.textHighLightsSecondary
                                ),
                                startY = 0f,
                                endY = 200f
                            ),
                            alpha = 0.88f
                        )
                        .padding(horizontal = 10.dp, vertical = 10.dp)
                ) {
                    Text(
                        text = if (currentStep >= 3) {
                            "Goal에 도달했어요!"
                        } else {
                            stringResource(R.string.reward_next_level, remainingPoints)
                        },
                        style = DhcTypoTokens.TitleH7,
                        color = colors.text.textHighLightsPrimary,
                        textAlign = TextAlign.Center,
                        maxLines = 1
                    )
                }
                
                // 삼각형 화살표 (툴팁 중앙에서 목표 위치로 offset)
                val triangleOffset = targetX - tooltipX - (tooltipWidth / 2)
                Box(
                    modifier = Modifier
                        .size(12.dp, 8.dp)
                        .offset(x = triangleOffset, y = (-1).dp)
                        .drawBehind {
                            val trianglePath = Path().apply {
                                moveTo(size.width / 2, size.height)
                                lineTo(0f, 0f)
                                lineTo(size.width, 0f)
                                close()
                            }
                            
                            drawPath(
                                path = trianglePath,
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        SurfaceColor.neutral30.copy(alpha = 0.88f),
                                        colors.text.textHighLightsSecondary.copy(alpha = 0.88f)
                                    ),
                                    startY = 0f,
                                    endY = size.height
                                )
                            )
                        }
                )
            }
        }

        // 프로그레스 바
        RewardProgressBar(
            currentStep = currentStep,
            modifier = Modifier.fillMaxWidth(),
            totalStepList = listOf("lv.1", "lv.4", "lv.8", "Goal")
        )
    }
}

@Composable
private fun PremiumFortuneCard() {
    val colors = LocalDhcColors.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(SurfaceColor.neutral700)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // 선물 아이콘
        Icon(
            painter = painterResource(com.dhc.designsystem.R.drawable.ico_present),
            contentDescription = null,
            modifier = Modifier.size(40.dp),
            tint = Color.Unspecified,
        )

        // Goal에 도달하면 프리미엄 운세를 볼 수 있어요
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Goal",
                style = DhcTypoTokens.TitleH7,
                color = colors.text.textHighLightsPrimary
            )
            Text(
                text = "에 도달하면 프리미엄 운세를 볼 수 있어요",
                style = DhcTypoTokens.Body5,
                color = SurfaceColor.neutral300
            )
        }

        // 프리미엄 운세 열기 버튼
        DhcButton(
            text = stringResource(R.string.reward_premium_fortune_button),
            buttonSize = DhcButtonSize.LARGE,
            buttonStyle = DhcButtonStyle.Secondary(false),
            onClick = { },
            modifier = Modifier.fillMaxWidth()
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

@Preview(name = "레벨 1 - 100pt", showBackground = true, backgroundColor = 0xFF0F1114)
@Composable
private fun RewardCardLevel1Preview() {
    DhcTheme {
        RewardCard(
            currentPoints = 100,
            currentStep = 0
        )
    }
}

@Preview(name = "레벨 2 - 400pt", showBackground = true, backgroundColor = 0xFF0F1114)
@Composable
private fun RewardCardLevel2Preview() {
    DhcTheme {
        RewardCard(
            currentPoints = 400,
            currentStep = 1
        )
    }
}

@Preview(name = "레벨 3 - 700pt", showBackground = true, backgroundColor = 0xFF0F1114)
@Composable
private fun RewardCardLevel3Preview() {
    DhcTheme {
        RewardCard(
            currentPoints = 700,
            currentStep = 2
        )
    }
}

@Preview(name = "레벨 4 - Goal 1600pt", showBackground = true, backgroundColor = 0xFF0F1114)
@Composable
private fun RewardCardLevel4Preview() {
    DhcTheme {
        RewardCard(
            currentPoints = 1600,
            currentStep = 3
        )
    }
}
