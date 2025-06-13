package com.dhc.designsystem.dialog

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.dhc.designsystem.DhcTheme
import com.dhc.designsystem.DhcTypoTokens
import com.dhc.designsystem.R
import com.dhc.designsystem.SurfaceColor

@Composable
fun DhcDialog(
    modifier: Modifier = Modifier,
    dialogContent: @Composable () -> Unit,
    dialogProperties: DialogProperties = DialogProperties(),
    onDisMissRequest: () -> Unit = {},
) {
    Dialog(
        properties = dialogProperties,
        onDismissRequest = onDisMissRequest,
    ) {
        Column(
            modifier = modifier
                .wrapContentSize()
                .background(color = SurfaceColor.neutral700, shape = RoundedCornerShape(12.dp))
                .padding(bottom = 16.dp),
        ) {
            IconButton (
                modifier = Modifier.align(Alignment.End).padding(top = 16.dp, end = 16.dp),
                onClick = { onDisMissRequest() }
            ) {
                Image(
                    painter = painterResource(R.drawable.x),
                    contentDescription = "closeButton"
                )
            }
            dialogContent()
        }
    }
}

@Preview
@Composable
fun DhcDialogPreview() {
    DhcTheme {
        DhcDialog(
            dialogContent = {
                Text(
                    text = stringResource(R.string.mission_complete_title),
                    style = DhcTypoTokens.TitleH2,
                    color = SurfaceColor.neutral100,
                    textAlign = TextAlign.Center,
                )
            }
        )
    }
}