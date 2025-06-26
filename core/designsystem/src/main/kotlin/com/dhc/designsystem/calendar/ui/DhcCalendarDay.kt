package com.dhc.designsystem.calendar.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.dhc.designsystem.DhcTheme
import com.dhc.designsystem.calendar.model.DhcCalendarDayUiModel

@Composable
fun DhcCalendarDay(
    day: Int,
    modifier: Modifier = Modifier,
    uiModel: DhcCalendarDayUiModel = DhcCalendarDayUiModel.DidNotMission,
) {
    Box(
        modifier = modifier
            .border(
                width = if (uiModel.borderColor != null) 2.dp else 0.dp,
                color = uiModel.borderColor ?: Color.Transparent,
                shape = RoundedCornerShape(8.dp)
            )
            .background(color = uiModel.backgroundColor, shape = RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = "$day",
            color = uiModel.textColor,
            style = uiModel.textStyle,
        )
    }
}

private class DhcCalendarDayPreviewProvider : PreviewParameterProvider<DhcCalendarDayUiModel> {
    override val values: Sequence<DhcCalendarDayUiModel>
        get() = sequenceOf(
            DhcCalendarDayUiModel.Today,
            DhcCalendarDayUiModel.DidNotMission,
            DhcCalendarDayUiModel.DidOneMission,
            DhcCalendarDayUiModel.DidTwoMission,
            DhcCalendarDayUiModel.DidMoreMission,
        )
}

@Preview
@Composable
private fun DhcCalendarDayPreview(
    @PreviewParameter(DhcCalendarDayPreviewProvider::class)
    uiModel: DhcCalendarDayUiModel,
) {
    DhcTheme {
        DhcCalendarDay(
            day = 1,
            uiModel = uiModel,
            modifier = Modifier
                .width(36.dp)
                .aspectRatio(1f)
        )
    }
}
