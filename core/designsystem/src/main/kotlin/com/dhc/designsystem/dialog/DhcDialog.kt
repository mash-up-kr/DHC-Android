package com.dhc.designsystem.dialog

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
    dialogProperties: DialogProperties = DialogProperties(),
    onDismissRequest: () -> Unit = {},
    dialogContent: @Composable () -> Unit,
) {
    Dialog(
        properties = dialogProperties,
        onDismissRequest = onDismissRequest,
    ) {
        Column(
            modifier = modifier
                .background(color = SurfaceColor.neutral700, shape = RoundedCornerShape(12.dp))
                .padding(bottom = 16.dp),
        ) {
            IconButton (
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(top = 16.dp, end = 12.dp)
                    .size(28.dp),
                onClick = { onDismissRequest() }
            ) {
                Image(
                    painter = painterResource(R.drawable.ico_x),
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
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(R.string.dialog_app_initialization_title),
                        style = DhcTypoTokens.TitleH6,
                        color = SurfaceColor.neutral400,
                        textAlign = TextAlign.Center,
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = stringResource(R.string.dialog_app_initialization_description),
                        style = DhcTypoTokens.TitleH6,
                        color = SurfaceColor.neutral400,
                        textAlign = TextAlign.Center,
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        )
    }
}
