package com.dhc.tooltip

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dhc.common.drawBalloonTail
import com.dhc.designsystem.DhcTheme
import com.dhc.designsystem.DhcTypoTokens
import com.dhc.designsystem.LocalDhcColors
import com.dhc.designsystem.SurfaceColor
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DhcTooltip(
    tooltipText: String,
    modifier: Modifier = Modifier,
    isPersistent: Boolean = true,
) {
    val tooltipState = rememberTooltipState(isPersistent = isPersistent)
    val coroutineScope = rememberCoroutineScope()
    val colors = LocalDhcColors.current

    TooltipBox(
        modifier = modifier,
        positionProvider = TooltipDefaults.rememberPlainTooltipPositionProvider(
            spacingBetweenTooltipAndAnchor = 10.dp
        ),
        tooltip = {
            Text(
                modifier = Modifier
                    .drawBalloonTail(
                        color = SurfaceColor.neutral500,
                        cornerWidth = 12.dp,
                        cornerHeight = 7.dp,
                    )
                    .background(SurfaceColor.neutral500, shape = RoundedCornerShape(8.dp))
                    .padding(10.dp),
                text = tooltipText,
                color = colors.text.textMain,
                style = DhcTypoTokens.Body5,
                textAlign = TextAlign.Center
            )
        },
        state = tooltipState
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

@Preview
@Composable
fun PreviewDhcTooltip() {
    DhcTheme {
        DhcTooltip(
            tooltipText = "좋은 금융 습관 형성을 위해\n" +
                    "2주간 매일 진행하는 미션이에요!",
        )
    }
}
