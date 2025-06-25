package com.dhc.designsystem.calendar.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.dhc.designsystem.AccentColor
import com.dhc.designsystem.DhcTypoTokens
import com.dhc.designsystem.LocalDhcColors
import com.dhc.designsystem.SurfaceColor
import java.time.LocalDate

sealed interface DhcCalendarDayUiModel {
    val backgroundColor: Color @Composable get
    val borderColor: Color? @Composable get
    val textStyle: TextStyle
    val textColor: Color @Composable get

    data object NoCurrentMonth: DhcCalendarDayUiModel {
        override val backgroundColor @Composable get() = SurfaceColor.neutral600
        override val borderColor @Composable get() = null
        override val textStyle = DhcTypoTokens.Body3
        override val textColor @Composable get() = SurfaceColor.neutral400
    }

    data object Today: DhcCalendarDayUiModel {
        override val backgroundColor @Composable get() = LocalDhcColors.current.background.backgroundBadgePrimary
        override val borderColor @Composable get() = LocalDhcColors.current.text.textHighLightsPrimary
        override val textStyle = DhcTypoTokens.TitleH5
        override val textColor @Composable get() = LocalDhcColors.current.text.textMain
    }

    data object DidNotMission: DhcCalendarDayUiModel {
        override val backgroundColor @Composable get() = SurfaceColor.neutral600
        override val borderColor @Composable get() = null
        override val textStyle = DhcTypoTokens.Body3
        override val textColor @Composable get() = LocalDhcColors.current.text.textBodyPrimary
    }

    data object DidOneMission: DhcCalendarDayUiModel {
        override val backgroundColor @Composable get() = AccentColor.violet400.copy(alpha = 0.35f)
        override val borderColor @Composable get() = null
        override val textStyle = DhcTypoTokens.Body3
        override val textColor @Composable get() = LocalDhcColors.current.text.textBodyPrimary
    }

    data object DidTwoMission: DhcCalendarDayUiModel {
        override val backgroundColor @Composable get() = AccentColor.violet400.copy(alpha = 0.7f)
        override val borderColor @Composable get() = null
        override val textStyle = DhcTypoTokens.Body3
        override val textColor @Composable get() = LocalDhcColors.current.text.textBodyPrimary
    }

    data object DidMoreMission: DhcCalendarDayUiModel {
        override val backgroundColor @Composable get() = LocalDhcColors.current.text.textHighLightsPrimary
        override val borderColor @Composable get() = null
        override val textStyle = DhcTypoTokens.Body3
        override val textColor @Composable get() = LocalDhcColors.current.text.textBodyPrimary
    }

    companion object {
        fun from(day: LocalDate, monthData: DhcCalendarMonthData): DhcCalendarDayUiModel {
            val finishedMissionCount = monthData.data[day.dayOfMonth]?.finishedMissionCount
            return when {
                day.month != monthData.yearMonth.month -> NoCurrentMonth
                day == LocalDate.now() -> Today
                finishedMissionCount == 0 -> DidNotMission
                finishedMissionCount == 1 -> DidOneMission
                finishedMissionCount == 2 -> DidTwoMission
                finishedMissionCount == 3 -> DidMoreMission
                else -> DidNotMission
            }
        }
    }
}
