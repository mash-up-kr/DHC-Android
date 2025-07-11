package com.dhc.designsystem.bottomsheet

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
import com.dhc.designsystem.R
import com.dhc.designsystem.SurfaceColor

@Composable
fun AlarmSettingBottomSheet(
    onDismissRequest: () -> Unit,
    onClickConfirmButton: () -> Unit,
) {
    val colors = LocalDhcColors.current
    DhcModalBottomSheet(
        isCloseButtonEnabled = true,
        containerColor = SurfaceColor.neutral700,
        onDismissRequest = onDismissRequest,
        content = {
            AlarmSettingContent(
                colors = colors,
                onClickConfirmButton = onClickConfirmButton
            )
        }
    )
}

@Composable
fun AlarmSettingContent(
    colors: DhcColors,
    onClickConfirmButton: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.alarm_setting_title),
            style = DhcTypoTokens.TitleH2,
            color = colors.text.textMain,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = stringResource(R.string.alarm_setting_cont),
            style = DhcTypoTokens.Body3,
            color = SurfaceColor.neutral200,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            modifier = Modifier.padding(bottom = 20.dp).fillMaxWidth(),
            onClick = onClickConfirmButton
        ) {
            Text(
                text = "임시버튼",
                style = DhcTypoTokens.TitleH2,
                color = colors.text.textMain,
                textAlign = TextAlign.Center,
            )
        }

    }
}

@Preview
@Composable
private fun PreviewAlarmSettingBottomSheet() {
    AlarmSettingBottomSheet(
        onDismissRequest = {},
        onClickConfirmButton = {}
    )
}