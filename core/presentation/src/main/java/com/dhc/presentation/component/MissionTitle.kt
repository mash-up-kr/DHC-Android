package com.dhc.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.dhc.designsystem.DhcTheme
import com.dhc.designsystem.DhcTypoTokens
import com.dhc.designsystem.LocalDhcColors
import com.dhc.designsystem.R
import com.dhc.designsystem.SurfaceColor
import com.dhc.designsystem.badge.DhcBadge
import com.dhc.designsystem.badge.model.BadgeType
import com.dhc.tooltip.DhcTooltip
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MissionTitle(
    title: String,
    modifier: Modifier = Modifier,
    tooltipMessage: String? = null,
    isEnableTooltip: Boolean = false,
    isInfoIconVisible: Boolean = false,
    spendTypeText: String? = null,
) {
    val colors = LocalDhcColors.current
    val tooltipState = rememberTooltipState(isPersistent = true)
    val coroutineScope = rememberCoroutineScope()

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = title,
            color = colors.text.textBodyPrimary,
            style = DhcTypoTokens.TitleH4_1,
        )
        if (isInfoIconVisible) {
            Spacer(modifier = Modifier.width(8.dp))
            if(isEnableTooltip) {
                tooltipMessage?.let {
                    DhcTooltip(
                        tooltipState = tooltipState,
                        tooltipText = tooltipMessage,
                        content = {
                            Image(
                                modifier = Modifier.clickable {
                                    coroutineScope.launch {
                                        tooltipState.show()
                                    }
                                },
                                painter = painterResource(R.drawable.ico_info_circle),
                                contentDescription = "information",
                                colorFilter = ColorFilter.tint(SurfaceColor.neutral400)
                            )
                        }
                    )
                }
            } else {
                Image(
                    painter = painterResource(R.drawable.ico_info_circle),
                    contentDescription = "information",
                    colorFilter = ColorFilter.tint(SurfaceColor.neutral400)
                )
            }
        }
        if (spendTypeText != null) {
            Spacer(modifier = Modifier.width(12.dp))
            DhcBadge(
                text = spendTypeText,
                type = BadgeType.SpendType,
            )
        }
    }
}

private class MissionTitlePreviewProvider :
    PreviewParameterProvider<MissionTitlePreviewProvider.Parameter> {
    override val values = sequenceOf(
        Parameter(
            title = "소비습관 미션",
            isInfoIconVisible = true,
            spendTypeText = "커피값 절약",
        ),
        Parameter(
            title = "소비습관 미션",
            isInfoIconVisible = false,
            spendTypeText = null,
        ),
    )

    data class Parameter(
        val title: String,
        val isInfoIconVisible: Boolean = false,
        val spendTypeText: String? = null,
    )
}

@Preview
@Composable
private fun MissionTitlePreview(
    @PreviewParameter(MissionTitlePreviewProvider::class)
    parameter: MissionTitlePreviewProvider.Parameter,
) {
    DhcTheme {
        MissionTitle(
            title = parameter.title,
            isInfoIconVisible = parameter.isInfoIconVisible,
            spendTypeText = parameter.spendTypeText,
        )
    }
}
