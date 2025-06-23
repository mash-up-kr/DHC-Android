package com.dhc.designsystem.graph

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.dhc.common.drawBalloonTail
import com.dhc.designsystem.AccentColor
import com.dhc.designsystem.DhcTheme
import com.dhc.designsystem.DhcTypoTokens
import com.dhc.designsystem.LocalDhcColors
import com.dhc.designsystem.SurfaceColor
import com.dhc.designsystem.graph.model.DhcGraphConfig
import com.dhc.designsystem.graph.model.DhcGraphData

@Composable
fun DhcGraph(
    graphData: List<DhcGraphData>,
    dhcGraphConfig: DhcGraphConfig,
    modifier: Modifier = Modifier,
) {
    val rowLabelStyle = DhcTypoTokens.Body6
    val columnLabelStyle = DhcTypoTokens.TitleH7

    ConstraintLayout(modifier = modifier) {
        val (graphYAxisLabels, graphHorizontalLines, graphBars, graphXAxisLabels) = createRefs()

        DhcGraphYAxisLabels(
            modifier = Modifier
                .padding(bottom = columnLabelStyle.fontSize.value.dp)
                .fillMaxHeight()
                .constrainAs(graphYAxisLabels) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                },
            labels = dhcGraphConfig.lineLabels,
            labelStyle = rowLabelStyle,
        )

        DhcGraphHorizontalLines(
            modifier = Modifier
                .padding(start = 16.dp, bottom = columnLabelStyle.fontSize.value.dp)
                .fillMaxHeight()
                .constrainAs(graphHorizontalLines) {
                    top.linkTo(graphYAxisLabels.top)
                    bottom.linkTo(graphYAxisLabels.bottom)
                    start.linkTo(graphYAxisLabels.end)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                },
            lines = dhcGraphConfig.lineLabels.size,
            textHeight = rowLabelStyle.fontSize.value.dp,
        )

        DhcGraphBars(
            data = graphData,
            maxValue = dhcGraphConfig.maxValue,
            modifier = Modifier
                .padding(
                    start = 16.dp,
                    top = rowLabelStyle.fontSize.value.dp / 2,
                    bottom = rowLabelStyle.fontSize.value.dp / 2 + columnLabelStyle.fontSize.value.dp,
                )
                .constrainAs(graphBars) {
                    bottom.linkTo(graphHorizontalLines.bottom)
                    start.linkTo(graphHorizontalLines.start)
                    end.linkTo(graphHorizontalLines.end)
                    top.linkTo(graphHorizontalLines.top)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                }
        )

        DhcGraphXAxisLabel(
            modifier = Modifier
                .padding(start = 16.dp)
                .fillMaxWidth()
                .constrainAs(graphXAxisLabels) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(graphHorizontalLines.start)
                    end.linkTo(graphHorizontalLines.end)
                    width = Dimension.fillToConstraints
                },
            datas = graphData,
        )
    }
}

@Composable
internal fun DhcGraphYAxisLabels(
    labels: List<String>,
    labelStyle: TextStyle,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        labels.forEach { label ->
            Text(
                text = label,
                color = SurfaceColor.neutral200,
                style = labelStyle,
            )
        }
    }
}

@Composable
private fun DhcGraphHorizontalLines(
    lines: Int,
    textHeight: Dp,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        repeat(lines) {
            Box(
                modifier = Modifier.height(textHeight),
                contentAlignment = Alignment.Center,
            ) {
                HorizontalDivider(
                    thickness = 1.dp,
                    color = SurfaceColor.neutral600,
                )
            }
        }
    }
}

@Composable
private fun DhcGraphBars(
    data: List<DhcGraphData>,
    maxValue: Int,
    modifier: Modifier = Modifier,
    graphWidth: Dp = 48.dp,
) {
    val colors = LocalDhcColors.current

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.Bottom,
    ) {
        data.forEach { dhcGraphData ->
            Box {
                DhcGraphBar(
                    modifier = Modifier
                        .width(graphWidth)
                        .fillMaxHeight(dhcGraphData.value / maxValue.toFloat()),
                    color = if (dhcGraphData.isHighlight) colors.text.textHighLightsPrimary else SurfaceColor.neutral400,
                )
                DhcGraphTooltip(
                    modifier = Modifier
                        .layout { measurable, constraints ->
                            val placeable = measurable.measure(constraints)
                            layout(0, placeable.height) {
                                placeable.place(
                                    -((placeable.width - graphWidth.toPx()) / 2).toInt(),
                                    -placeable.height
                                )
                            }
                        }
                        .padding(bottom = 12.dp),
                    style = if (dhcGraphData.isHighlight) DhcTypoTokens.TitleH7 else DhcTypoTokens.Body5,
                    text = dhcGraphData.tooltipMessage,
                    tooltipColor = SurfaceColor.neutral600,
                    textColor = if (dhcGraphData.isHighlight) AccentColor.violet300 else SurfaceColor.neutral200,
                )
            }
        }
    }
}

@Composable
private fun DhcGraphBar(
    color: Color,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .clip(
                shape = RoundedCornerShape(
                    topStart = 8.dp,
                    topEnd = 8.dp,
                )
            )
            .background(color = color),
    )
}

@Composable
private fun DhcGraphXAxisLabel(
    datas: List<DhcGraphData>,
    modifier: Modifier = Modifier,
) {
    val colors = LocalDhcColors.current

    Row(modifier = modifier) {
        datas.forEach { data ->
            Text(
                text = data.label,
                color = if (data.isHighlight) colors.text.textHighLightsSecondary else SurfaceColor.neutral300,
                modifier = Modifier.weight(1f),
                style = if (data.isHighlight) DhcTypoTokens.TitleH7 else DhcTypoTokens.Body5,
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Composable
private fun DhcGraphTooltip(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = DhcTypoTokens.Body5,
    tooltipColor: Color = SurfaceColor.neutral600,
    textColor: Color = SurfaceColor.neutral200,
    cornerWidth: Dp = 12.dp,
    cornerHeight: Dp = 8.dp
) {
    Text(
        modifier = modifier
            .drawBalloonTail(
                color = tooltipColor,
                cornerWidth = cornerWidth,
                cornerHeight = cornerHeight,
            )
            .background(color = tooltipColor, shape = RoundedCornerShape(4.dp))
            .padding(vertical = 8.dp, horizontal = 16.dp),
        text = text,
        style = style,
        color = textColor,
    )
}

@Preview
@Composable
private fun DhcGraphPreview() {
    DhcTheme {
        DhcGraph(
            graphData = listOf(
                DhcGraphData(
                    isHighlight = true,
                    value = 100000,
                    label = "10만원",
                    tooltipMessage = "안녕"
                ),
                DhcGraphData(value = 75000, label = "7만원", tooltipMessage = "안녕하세요 ~~"),
            ),
            dhcGraphConfig = DhcGraphConfig(
                lineLabels = listOf("10 만원", "7.5 만원", "5 만원", "2.5 만원", "0 원"),
                maxValue = 100000,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
        )
    }
}
