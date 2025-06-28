package com.dhc.home.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dhc.designsystem.DhcTheme
import com.dhc.designsystem.DhcTypoTokens
import com.dhc.designsystem.LocalDhcColors
import com.dhc.designsystem.SurfaceColor
import com.dhc.designsystem.bottomsheet.DhcModalBottomSheet
import com.dhc.designsystem.button.DhcButton
import com.dhc.designsystem.button.model.DhcButtonSize
import com.dhc.designsystem.button.model.DhcButtonStyle
import com.dhc.home.R
import com.dhc.home.model.MissionChangeButtonType
import com.dhc.presentation.mvi.EventHandler

@Composable
fun MissionChangeBottomSheet(
    eventHandler: EventHandler<HomeContract.Event>,
) {
    DhcModalBottomSheet(
        isCloseButtonEnabled = false,
        containerColor = SurfaceColor.neutral700,
        onDismissRequest = {},
        content = {
            MissionChangeContent(
                missionTitle = "가까운 거리 걸어다니기",
                missionChangeCount = 0,
                onClickDismiss = { eventHandler(HomeContract.Event.ClickMissionChangeConfirm(MissionChangeButtonType.BACK))},
                onClickChange = { eventHandler(HomeContract.Event.ClickMissionChangeConfirm(MissionChangeButtonType.CHANGE))},
            )
        }
    )
}

@Composable
fun MissionChangeContent(
    missionTitle: String,
    missionChangeCount: Int,
    onClickChange: () -> Unit,
    onClickDismiss: () -> Unit,
) {
    val colors = LocalDhcColors.current
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = stringResource(R.string.mission_change_title, missionTitle),
            style = DhcTypoTokens.TitleH2_1,
            color = colors.text.textMain,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = stringResource(R.string.mission_change_description, missionChangeCount),
            style = DhcTypoTokens.Body3,
            color = SurfaceColor.neutral200,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(32.dp))
        DhcButton(
            text = stringResource(R.string.accept_change),
            buttonSize = DhcButtonSize.XLARGE,
            buttonStyle = DhcButtonStyle.Primary(isEnabled = true),
            onClick = onClickChange,
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        DhcButton(
            text = stringResource(R.string.back),
            buttonSize = DhcButtonSize.XLARGE,
            buttonStyle = DhcButtonStyle.Teritary,
            onClick = onClickDismiss,
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(20.dp))

    }
}

@Preview
@Composable
private fun PreviewMissionChangeBottomSheet() {
    DhcTheme {
        MissionChangeBottomSheet(
            eventHandler = {}
        )
    }
}