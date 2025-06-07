package com.dhc.designsystem.bottomsheet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlarmSettingBottomSheet() {
    val colors = LocalDhcColors.current
    DhcModalBottomSheet(
        isCloseButtonEnabled = true,
        containerColor = SurfaceColor.neutral700,
        onDismissRequest = {},
        content = {
            AlarmSettingContent(
                colors = colors
            )
        }
    )
}

@Composable
fun AlarmSettingContent(
    colors: DhcColors,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //TODO - 추후 버튼과 타이틀은 DHC Component로 변경 필요
        Text(
            text = stringResource(R.string.alarm_setting_title),
            style = DhcTypoTokens.TitleH2,
            color = colors.text.textMain,
            textAlign = TextAlign.Center,
        )
        Text(
            text = stringResource(R.string.alarm_setting_cont),
            style = DhcTypoTokens.Body3,
            color = colors.text.textBodyPrimary,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            modifier = Modifier.padding(bottom = 20.dp).fillMaxWidth(),
            onClick = {}
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
fun PreviewAlarmSettingBottomSheet() {
    AlarmSettingBottomSheet()
}