package com.dhc.reward.yearfortune

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.dhc.designsystem.DhcTheme
import com.dhc.designsystem.DhcTypoTokens
import com.dhc.designsystem.LocalDhcColors
import com.dhc.designsystem.SurfaceColor
import com.dhc.reward.R

// 오행 요소 타입
enum class FiveElementType(
    val koreanName: String,
    val character: String,
    val color: Color
) {
    WOOD("목", "木", Color(0xFF4ECDC4)), // 목기운 - 청록색
    FIRE("화", "火", Color(0xFFFF6B6B)), // 화기운 - 빨간색
    EARTH("토", "土", Color(0xFFFFD93D)), // 토기운 - 노란색
    METAL("금", "金", Color(0xFFC0C0C0)), // 금기운 - 은색
    WATER("수", "水", Color(0xFFB19CD9))  // 수기운 - 보라색
}

// 오행 상태
enum class FiveElementStatus(
    val koreanName: String,
    val color: Color
) {
    EXCESSIVE("과다", Color(0xFFFF6F6F)),   // 40% 이상 - 빨간색
    APPROPRIATE("적정", Color(0xFF02B579)), // 20~30% - 초록색
    INSUFFICIENT("부족", Color(0xFF70A2FF)) // 10% 이하 - 연한 파란색
}

// 오행 데이터 모델
data class FiveElementData(
    val type: FiveElementType,
    val percentage: Int,
    val status: FiveElementStatus
) {
    companion object {
        fun fromPercentage(type: FiveElementType, percentage: Int): FiveElementData {
            val status = when {
                percentage >= 40 -> FiveElementStatus.EXCESSIVE
                percentage in 20..30 -> FiveElementStatus.APPROPRIATE
                percentage <= 10 -> FiveElementStatus.INSUFFICIENT
                else -> FiveElementStatus.APPROPRIATE
            }
            return FiveElementData(type, percentage, status)
        }
    }
}

@Composable
fun FiveElementGaugeContent(
    modifier: Modifier = Modifier,
    fiveElementData: List<FiveElementData> = defaultFiveElementData()
) {
    val colors = LocalDhcColors.current

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.next_year_five_gauge),
            style = DhcTypoTokens.TitleH4_1,
            color = colors.text.textMain,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "내년엔 화기운이 너무 강해져요!\n" +
                    "화기운을 조심해야 해요~",
            style = DhcTypoTokens.TitleH4_1,
            color = colors.text.textMain,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
        FiveElementGaugeChart(
            data = fiveElementData,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun FiveElementGaugeChart(
    data: List<FiveElementData>,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.Bottom
    ) {
        data.forEach { elementData ->
            FiveElementGaugeItem(
                data = elementData,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun FiveElementGaugeItem(
    data: FiveElementData,
    modifier: Modifier = Modifier
) {
    val colors = LocalDhcColors.current
    val gaugeHeight = 166.dp
    val gaugeWidth = 30.dp
    val fillHeight = (gaugeHeight * (data.percentage / 100f))

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        // 퍼센트 표시
        Text(
            text = "${data.percentage}%",
            style = DhcTypoTokens.Body7,
            color = colors.text.textBodyPrimary,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(12.dp))

        Box(
            modifier = Modifier
                .width(gaugeWidth)
                .height(gaugeHeight),
            contentAlignment = Alignment.BottomCenter
        ) {
            // 백그라운드 바
            Box(
                modifier = Modifier
                    .width(10.dp)
                    .height(gaugeHeight - 4.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .background(SurfaceColor.neutral500)
            )

            // 포그라운드 바 (채워진 부분) - 상태에 따른 색상
            if (data.percentage > 0) {
                Box(
                    modifier = Modifier
                        .width(10.dp)
                        .height(fillHeight - 4.dp)
                        .clip(RoundedCornerShape(5.dp))
                        .background(data.status.color)
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // 한자 아이콘 - 상태에 따른 색상
        Box(
            modifier = Modifier
                .size(24.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(data.status.color),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = data.type.character,
                style = DhcTypoTokens.Body5,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        // 상태 텍스트 - 상태에 따른 색상
        Text(
            text = data.status.koreanName,
            style = DhcTypoTokens.Body7,
            color = data.status.color,
            textAlign = TextAlign.Center
        )
    }
}

private fun defaultFiveElementData(): List<FiveElementData> {
    return listOf(
        FiveElementData.fromPercentage(FiveElementType.WOOD, 20),
        FiveElementData.fromPercentage(FiveElementType.FIRE, 40),
        FiveElementData.fromPercentage(FiveElementType.EARTH, 30),
        FiveElementData.fromPercentage(FiveElementType.METAL, 20),
        FiveElementData.fromPercentage(FiveElementType.WATER, 10)
    )
}

@Preview(name = "오행 게이지 그래프", showBackground = true, backgroundColor = 0xFF0F1114)
@Composable
private fun YearFortuneDetailScreenPreview() {
    DhcTheme {
        FiveElementGaugeContent()
    }
}