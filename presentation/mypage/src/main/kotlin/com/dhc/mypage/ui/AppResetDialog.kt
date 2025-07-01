package com.dhc.mypage.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.dhc.designsystem.button.DhcButton
import com.dhc.designsystem.button.model.DhcButtonSize
import com.dhc.designsystem.button.model.DhcButtonStyle
import com.dhc.designsystem.dialog.DhcDialog
import com.dhc.mypage.R

@Composable
fun AppResetDialog(
    onClickAppResetButton: () -> Unit,
    onClickDismissButton: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = LocalDhcColors.current

    DhcDialog(
        modifier = modifier,
        onClickDismiss = onClickDismissButton,
    ) {
        Column(
            modifier = Modifier.padding(top = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .padding(top = 12.dp)
                    .fillMaxWidth(),
                text = stringResource(R.string.app_reset_dialog_title),
                style = DhcTypoTokens.TitleH3,
                color = colors.text.textBodyPrimary,
                textAlign = TextAlign.Center,
            )
            Text(
                modifier = Modifier
                    .padding(top = 4.dp)
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth(),
                text = stringResource(R.string.app_reset_dialog_sub_title),
                style = DhcTypoTokens.TitleH6,
                color = SurfaceColor.neutral400,
                textAlign = TextAlign.Center,
            )
            DhcButton(
                modifier = Modifier
                    .padding(top = 12.dp)
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth(),
                text = stringResource(R.string.app_reset_dialog_cancel),
                buttonSize = DhcButtonSize.LARGE,
                buttonStyle = DhcButtonStyle.Primary(true),
                onClick = onClickDismissButton
            )
            DhcButton(
                modifier = Modifier
                    .padding(top = 4.dp)
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth(),
                text = stringResource(R.string.app_reset_dialog_ok),
                buttonSize = DhcButtonSize.LARGE,
                buttonStyle = DhcButtonStyle.Teritary,
                onClick = onClickAppResetButton
            )
        }
    }
}

@Preview
@Composable
private fun AppResetDialogPreview() {
    DhcTheme {
        AppResetDialog(
            onClickAppResetButton = {},
            onClickDismissButton = {},
        )
    }
}
