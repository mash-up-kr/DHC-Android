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
fun MissionCompleteCheckBottomSheet(
    missionCount: Int,
    onDismissRequest: () -> Unit,
) {
    val colors = LocalDhcColors.current
    DhcModalBottomSheet(
        isCloseButtonEnabled = false,
        containerColor = SurfaceColor.neutral700,
        onDismissRequest = onDismissRequest,
        content = {
            MissionCompleteCheckContent(
                missionCount = missionCount,
                colors = colors
            )
        }
    )
}

@Composable
fun MissionCompleteCheckContent(
    missionCount: Int,
    colors: DhcColors,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = stringResource(R.string.mission_complete_title),
            style = DhcTypoTokens.TitleH2,
            color = colors.text.textMain,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = stringResource(R.string.mission_complete_desc, missionCount),
            style = DhcTypoTokens.Body3,
            color = SurfaceColor.neutral200,
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
        Spacer(modifier = Modifier.height(20.dp))

    }
}

@Preview
@Composable
private fun PreviewMissionCompleteCheckBottomSheet() {
    MissionCompleteCheckBottomSheet(
        onDismissRequest = {},
        missionCount = 0
    )
}