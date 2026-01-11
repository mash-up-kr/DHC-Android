package com.dhc.home.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
import com.dhc.home.R
import com.dhc.home.model.MissionFailType
import com.dhc.home.model.MissionSuccessButtonType
import com.dhc.presentation.mvi.EventHandler


@Composable
fun MissionFailDialog(
    missionFailType: MissionFailType,
    eventHandler: EventHandler<HomeContract.Event>,
    modifier: Modifier = Modifier,
) {
    val colors = LocalDhcColors.current
    DhcDialog(
        modifier = modifier,
        onClickDismiss = { eventHandler(HomeContract.Event.ClickMissionSuccess(MissionSuccessButtonType.Confirm)) },
        dialogContent = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    DhcBadge(
                        text = stringResource(R.string.mission_fail),
                        BadgeType.Date
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(brush = GradientColor.textGradient01)) {
                                append(missionFailType.mainMessage)
                            }
                        },
                        style = DhcTypoTokens.TitleH4,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = missionFailType.subMessage,
                        style = DhcTypoTokens.Body5,
                        color = SurfaceColor.neutral300,
                        textAlign = TextAlign.Center
                    )
                }
                Image(
                    painter = painterResource(R.drawable.mission_success),
                    contentDescription = "missionFail",
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(12.dp))
                DhcButton(
                    text = stringResource(R.string.confirm),
                    buttonSize = DhcButtonSize.LARGE,
                    buttonStyle = DhcButtonStyle.Teritary,
                    onClick = { eventHandler(HomeContract.Event.ClickMissionFailConfirmButton) },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    )

}

@Preview
@Composable
fun MissionFailPreview() {
    MissionFailDialog(
        missionFailType = MissionFailType.NEXT_DAY_REENTRY,
        eventHandler = {}
    )
}