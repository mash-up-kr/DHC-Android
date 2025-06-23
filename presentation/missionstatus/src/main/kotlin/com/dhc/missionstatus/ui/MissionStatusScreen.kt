package com.dhc.missionstatus.ui

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dhc.designsystem.DhcTheme
import com.dhc.designsystem.DhcTypoTokens
import com.dhc.designsystem.LocalDhcColors
import com.dhc.designsystem.SurfaceColor
import com.dhc.designsystem.calendar.ui.DhcCalendar
import com.dhc.designsystem.graph.DhcGraph
import com.dhc.designsystem.graph.model.DhcGraphConfig
import com.dhc.designsystem.graph.model.DhcGraphData
import com.dhc.designsystem.info.DhcMissionInfoCard
import com.dhc.designsystem.info.DhcMissionStatusCard
import com.dhc.missionstatus.MissionStatusContract.State
import com.dhc.missionstatus.R

@Composable
fun MissionStatusScreen(
    state: State,
    modifier: Modifier = Modifier,
    scrollState: ScrollState = rememberScrollState()
) {
    val colors = LocalDhcColors.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(color = SurfaceColor.neutral800) // Todo :: 배경색 변경 필요
            .padding(20.dp)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp, bottom = 22.dp),
            text = stringResource(R.string.missions_status_screen_title),
            style = DhcTypoTokens.TitleH2_1,
            color = colors.text.textBodyPrimary,
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            text = stringResource(R.string.consumption_analysis_title),
            style = DhcTypoTokens.Body3,
            color = SurfaceColor.neutral30,
        )

        DhcMissionStatusCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            title = stringResource(R.string.save_total_money_until_now),
            subTitle = "130,000원 아꼈어요!",
        )

        ConsumptionAnalysisContent(
            modifier = Modifier.padding(top = 12.dp),
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            text = stringResource(R.string.mission_analysis_title),
            style = DhcTypoTokens.Body3,
            color = SurfaceColor.neutral30,
        )

        DhcMissionStatusCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            title = "5월달",
            subTitle = "미션 평균 성공률 82%",
        )

        DhcCalendar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            DhcMissionInfoCard(
                modifier = Modifier.weight(1f),
                categoryText = "식음료",
                categoryTextColor = Color(0xFFFFC84D),
                message = "미션을 잘 수행\n하고 있어요",
                totalMissionCount = 20,
                currentMissionCount = 12,
            )
            DhcMissionInfoCard(
                modifier = Modifier.weight(1f),
                categoryText = "쇼핑",
                categoryTextColor = Color(0xFF52D1FF),
                message = "미션에 어려움을\n겪고 있어요",
                totalMissionCount = 18,
                currentMissionCount = 4,
            )
        }
    }
}

@Composable
private fun ConsumptionAnalysisContent(modifier: Modifier = Modifier) {
    val colors = LocalDhcColors.current

    Column(
        modifier = modifier
            .background(color = SurfaceColor.neutral700, shape = RoundedCornerShape(8.dp))
            .padding(20.dp),
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = colors.text.textHighLightsSecondary)) {
                    append("이번주에  ")
                }
                append("20대 남성 대비")
            },
            style = DhcTypoTokens.TitleH3,
            color = colors.text.textMain,
        )
        Text(
            modifier = Modifier
                .padding(top = 4.dp)
                .fillMaxWidth(),
            text = buildAnnotatedString {
                append("28,000원 ")
                withStyle(style = SpanStyle(color = colors.text.textHighLightsSecondary)) {
                    append("더절약했어요")
                }
            },
            style = DhcTypoTokens.TitleH3,
            color = colors.text.textMain,
        )
        DhcGraph(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(top = 40.dp),
            dhcGraphConfig = DhcGraphConfig(
                maxValue = 100_000,
                lineLabels = listOf("10 만원", "7.5 만원", "5 만원", "2.5 만원", "0 원")
            ),
            graphData = listOf(
                DhcGraphData(
                    label = "나",
                    value = 52_000,
                    isHighlight = true,
                    tooltipMessage = "52,000원",
                ),
                DhcGraphData(
                    label = "20대 남성",
                    value = 80_000,
                    isHighlight = false,
                    tooltipMessage = "80,000원",
                )
            )
        )
    }
}

@Preview
@Composable
private fun MissionStatusScreenPreview() {
    DhcTheme {
        MissionStatusScreen(state = State())
    }
}
