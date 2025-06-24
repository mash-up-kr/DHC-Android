package com.dhc.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
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
import com.dhc.designsystem.SurfaceColor
import com.dhc.designsystem.badge.DhcBadge
import com.dhc.designsystem.badge.model.BadgeType
import com.dhc.designsystem.button.DhcButton
import com.dhc.designsystem.button.model.DhcButtonSize
import com.dhc.designsystem.button.model.DhcButtonStyle
import com.dhc.designsystem.dialog.DhcDialog

@Composable
fun MissionSuccessDialog() {
    DhcDialog(
        dialogContent = {
            Column(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                DhcBadge(
                    text = stringResource(R.string.mission_success),
                    BadgeType.Date
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = stringResource(R.string.great),
                    style = DhcTypoTokens.Body2,
                    color = SurfaceColor.neutral200
                )
                Text(
                    text = buildAnnotatedString {
                        append("오늘 총 ")
                        withStyle(style = SpanStyle(brush = GradientColor.textGradient01)) {
                            append("3300원")
                        }
                        append("을\n절약했어요")
                    },
                    style = DhcTypoTokens.TitleH2_1,
                    color = SurfaceColor.neutral200,
                    textAlign = TextAlign.Center
                )
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .fillMaxWidth()
                        .height(216.dp)
                        .background(brush = GradientColor.backgroundGradient01),
                ) {}
                DhcButton(
                    text = stringResource(R.string.confirm_statics),
                    buttonSize = DhcButtonSize.MEDIUM,
                    buttonStyle = DhcButtonStyle.Primary(isEnabled = true),
                    onClick = {  },
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(4.dp))
                DhcButton(
                    text = stringResource(R.string.already_confirm),
                    buttonSize = DhcButtonSize.MEDIUM,
                    buttonStyle = DhcButtonStyle.Teritary,
                    onClick = { },
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
        MissionSuccessDialog()
    }
}