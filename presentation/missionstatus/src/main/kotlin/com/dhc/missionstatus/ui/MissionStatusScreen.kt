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
import com.dhc.common.FormatterUtil.wonFormat
import com.dhc.designsystem.DhcTheme
import com.dhc.designsystem.DhcTypoTokens
import com.dhc.designsystem.LocalDhcColors
import com.dhc.designsystem.SurfaceColor
import com.dhc.designsystem.calendar.DhcCalendarController
import com.dhc.designsystem.calendar.model.DhcCalendarInitialData
import com.dhc.designsystem.calendar.ui.DhcCalendar
import com.dhc.designsystem.graph.DhcGraph
import com.dhc.designsystem.graph.model.DhcGraphConfig
import com.dhc.designsystem.graph.model.DhcGraphData
import com.dhc.designsystem.info.DhcMissionStatusCard
import com.dhc.missionstatus.MissionStatusContract.State
import com.dhc.missionstatus.R
import com.dhc.presentation.component.TopGradiantBox
import java.time.LocalDate

@Composable
fun MissionStatusScreen(
    state: State,
    modifier: Modifier = Modifier,
    scrollState: ScrollState = rememberScrollState(),
    calendarController: DhcCalendarController = DhcCalendarController(
        initialData = DhcCalendarInitialData(initialDate = LocalDate.now())
    )
) {
    val colors = LocalDhcColors.current

    TopGradiantBox(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
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

            if (state.consumptionAnalysisUiModel != null) {
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
                    title = stringResource(R.string.save_total_money_until_now_prefix),
                    subTitle = stringResource(
                        R.string.save_total_money_until_now,
                        wonFormat.format(state.consumptionAnalysisUiModel.totalSaveMoney)
                    ),
                )

                ConsumptionAnalysisContent(
                    weeklySaveMoney = state.consumptionAnalysisUiModel.weeklySaveMoney,
                    graphData = state.consumptionAnalysisUiModel.graphData,
                    modifier = Modifier.padding(top = 12.dp),
                )
            }

            if (state.missionAnalysisUiModel != null) {
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
                    title = "${state.missionAnalysisUiModel.currentMonth}월달",
                    subTitle = "미션 평균 성공률 ${state.missionAnalysisUiModel.averageSucceedProbability}%",
                )

                DhcCalendar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp),
                    initialDate = LocalDate.now(),
                    controller = calendarController,
                )
            }
        }
    }
}

@Composable
private fun ConsumptionAnalysisContent(
    weeklySaveMoney: Int,
    graphData: AnalysisGraphUiModel,
    modifier: Modifier = Modifier
) {
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
                    append(stringResource(R.string.consumption_analysis_this_week))
                }
                append(stringResource(R.string.consumption_analysis_target, graphData.target))
            },
            style = DhcTypoTokens.TitleH3,
            color = colors.text.textMain,
        )
        Text(
            modifier = Modifier
                .padding(top = 4.dp)
                .fillMaxWidth(),
            text = buildAnnotatedString {
                append(
                    stringResource(
                        R.string.consumption_analysis_money,
                        wonFormat.format(weeklySaveMoney)
                    )
                )
                withStyle(style = SpanStyle(color = colors.text.textHighLightsSecondary)) {
                    append(stringResource(R.string.consumption_analysis_save_more_money))
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
                maxValue = 120_000,
                lineLabels = listOf(12, 9, 6, 3, 0).map { stringResource(R.string.consumption_analysis_calendar_man_won, it) }
            ),
            graphData = listOf(
                DhcGraphData(
                    label = stringResource(R.string.consumption_analysis_calendar_me),
                    value = weeklySaveMoney,
                    isHighlight = true,
                    tooltipMessage = stringResource(
                        R.string.consumption_analysis_calendar_won,
                        weeklySaveMoney
                    ),
                ),
                DhcGraphData(
                    label = graphData.target,
                    value = graphData.targetData,
                    isHighlight = false,
                    tooltipMessage = stringResource(
                        R.string.consumption_analysis_calendar_won,
                        wonFormat.format(graphData.targetData),
                    ),
                )
            )
        )
    }
}

@Preview
@Composable
private fun MissionStatusScreenPreview() {
    DhcTheme {
        MissionStatusScreen(
            state = State(
                missionAnalysisUiModel = MissionAnalysisUiModel(
                    currentMonth = 10,
                    averageSucceedProbability = 75,
                ),
                consumptionAnalysisUiModel = ConsumptionAnalysisUiModel(),
            )
        )
    }
}
