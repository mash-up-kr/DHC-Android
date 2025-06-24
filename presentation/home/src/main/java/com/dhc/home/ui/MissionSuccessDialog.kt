package com.dhc.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dhc.designsystem.DhcTheme
import com.dhc.designsystem.DhcTypoTokens
import com.dhc.designsystem.GradientColor
import com.dhc.designsystem.LocalDhcColors
import com.dhc.designsystem.SurfaceColor
import com.dhc.designsystem.badge.DhcBadge
import com.dhc.designsystem.badge.model.BadgeType
import com.dhc.designsystem.button.DhcButton
import com.dhc.designsystem.button.model.DhcButtonSize
import com.dhc.designsystem.button.model.DhcButtonStyle
import com.dhc.designsystem.dialog.DhcDialog
import com.dhc.home.HomeContract
import com.dhc.home.R
import com.dhc.home.model.MissionSuccessButtonType
import com.dhc.presentation.mvi.EventHandler

@Composable
fun MissionSuccessDialog(
    eventHandler: EventHandler<HomeContract.Event>,
    modifier: Modifier = Modifier,
) {
    val colors = LocalDhcColors.current

    DhcDialog(
        modifier = modifier,
        onDisMissRequest = { eventHandler(HomeContract.Event.ClickMissionSuccess(MissionSuccessButtonType.Confirm)) },
        dialogContent = {
            Column(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                DhcBadge(
                    text = stringResource(R.string.mission_success),
                    BadgeType.Date
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = stringResource(R.string.great),
                    style = DhcTypoTokens.Body2,
                    color = SurfaceColor.neutral200
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = buildAnnotatedString {
                        append(stringResource(R.string.today_total))
                        append(" ")
                        withStyle(style = SpanStyle(brush = GradientColor.textGradient01)) {
                            append(stringResource(R.string.amount, "3300"))
                        }
                        append(stringResource(R.string.save_description))
                    },
                    style = DhcTypoTokens.TitleH2_1,
                    color = colors.text.textMain,
                    textAlign = TextAlign.Center
                )
                Box(
                    modifier = Modifier
                        .padding(vertical = 12.dp)
                        .align(Alignment.CenterHorizontally)
                        .fillMaxWidth()
                        .height(216.dp)
                        .background(brush = GradientColor.backgroundGradient01),
                ) {}
                DhcButton(
                    text = stringResource(R.string.confirm_statics),
                    buttonSize = DhcButtonSize.MEDIUM,
                    buttonStyle = DhcButtonStyle.Primary(isEnabled = true),
                    onClick = { eventHandler(HomeContract.Event.ClickMissionSuccess(MissionSuccessButtonType.StaticConfirm))  },
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(4.dp))
                DhcButton(
                    text = stringResource(R.string.already_confirm),
                    buttonSize = DhcButtonSize.MEDIUM,
                    buttonStyle = DhcButtonStyle.Teritary,
                    onClick = { eventHandler(HomeContract.Event.ClickMissionSuccess(MissionSuccessButtonType.Confirm)) },
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    )
}

@Preview
@Composable
fun PreviewMissionSuccessDialog() {
    DhcTheme {
        MissionSuccessDialog(
            eventHandler = {}
        )
    }
}