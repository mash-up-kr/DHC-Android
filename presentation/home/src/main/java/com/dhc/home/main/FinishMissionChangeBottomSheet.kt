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
import com.dhc.presentation.mvi.EventHandler
import com.dhc.designsystem.R as DR

@Composable
fun FinishMissionChangeBottomSheet(
    eventHandler: EventHandler<HomeContract.Event>,
) {
    DhcModalBottomSheet(
        isCloseButtonEnabled = true,
        containerColor = SurfaceColor.neutral700,
        onDismissRequest = { eventHandler(HomeContract.Event.ClickFinishMissionChangeConfirm) },
        content = {
            FinishMissionChangeContent(
                onClickConfirm = { eventHandler(HomeContract.Event.ClickFinishMissionChangeConfirm) }
            )
        }
    )
}

@Composable
fun FinishMissionChangeContent(
    onClickConfirm: () -> Unit,
) {
    val colors = LocalDhcColors.current
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.limit_mission_change),
            style = DhcTypoTokens.TitleH2_1,
            color = colors.text.textMain,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = stringResource(R.string.limit_mission_change_description),
            style = DhcTypoTokens.Body3,
            color = SurfaceColor.neutral200,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(32.dp))
        DhcButton(
            text = stringResource(DR.string.confirm),
            buttonSize = DhcButtonSize.XLARGE,
            buttonStyle = DhcButtonStyle.Primary(isEnabled = true),
            onClick = onClickConfirm,
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Preview
@Composable
private fun PreviewFinishMissionChangeBottomSheet() {
    DhcTheme {
        FinishMissionChangeBottomSheet(
            eventHandler = {}
        )
    }
}