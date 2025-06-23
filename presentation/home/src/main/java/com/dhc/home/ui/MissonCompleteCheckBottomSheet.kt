package com.dhc.home.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dhc.designsystem.DhcColors
import com.dhc.designsystem.DhcTypoTokens
import com.dhc.designsystem.LocalDhcColors
import com.dhc.designsystem.R as dR
import com.dhc.designsystem.SurfaceColor
import com.dhc.designsystem.bottomsheet.DhcModalBottomSheet
import com.dhc.designsystem.button.DhcButton
import com.dhc.designsystem.button.model.DhcButtonSize
import com.dhc.designsystem.button.model.DhcButtonStyle
import com.dhc.home.HomeContract
import com.dhc.home.R
import com.dhc.presentation.mvi.EventHandler

@Composable
fun MissionCompleteCheckBottomSheet(
    missionCount: Int,
    eventHandler: EventHandler<HomeContract.Event>,
) {
    val colors = LocalDhcColors.current
    DhcModalBottomSheet(
        isCloseButtonEnabled = false,
        containerColor = SurfaceColor.neutral700,
        onDismissRequest = {},
        content = {
            MissionCompleteCheckContent(
                missionCount = missionCount,
                colors = colors,
                onClickFinish = { eventHandler(HomeContract.Event.ClickFinishMissionButton) },
                onClickDismiss =  { eventHandler(HomeContract.Event.DismissMissionComplete) }
            )
        }
    )
}

@Composable
fun MissionCompleteCheckContent(
    missionCount: Int,
    colors: DhcColors,
    onClickFinish: () -> Unit,
    onClickDismiss: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = stringResource(dR.string.mission_complete_title),
            style = DhcTypoTokens.TitleH2,
            color = colors.text.textMain,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = stringResource(dR.string.mission_complete_desc, missionCount),
            style = DhcTypoTokens.Body3,
            color = SurfaceColor.neutral200,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(32.dp))
        DhcButton(
            text = stringResource(R.string.finish_mission),
            buttonSize = DhcButtonSize.XLARGE,
            buttonStyle = DhcButtonStyle.Primary(isEnabled = true),
            onClick = { onClickFinish() },
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        DhcButton(
            text = stringResource(R.string.go_back),
            buttonSize = DhcButtonSize.XLARGE,
            buttonStyle = DhcButtonStyle.Teritary,
            onClick = { onClickDismiss() },
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(20.dp))

    }
}

@Preview
@Composable
private fun PreviewMissionCompleteCheckBottomSheet() {
    MissionCompleteCheckBottomSheet(
        eventHandler = {},
        missionCount = 0,
    )
}