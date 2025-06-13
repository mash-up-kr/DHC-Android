package com.dhc.designsystem

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dhc.designsystem.check.DhcCheck
import com.dhc.designsystem.check.model.DhcCheckStyle

@Composable
fun DhcSnackBar(
    snackBarHostState: SnackbarHostState,
    snackBarContent: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) {
    SnackbarHost(
        modifier = modifier
            .padding(start = 20.dp, end = 20.dp, bottom = 24.dp)
            .clip(shape = RoundedCornerShape(12.dp)),
        hostState = snackBarHostState,
    ) {
        snackBarContent()
    }
}

@Composable
fun SnackBarContent(
    snackBarMessage: String,
    modifier: Modifier = Modifier,
    snackBarActionIcon: @Composable () -> Unit = {
        DhcCheck(
            isChecked = true,
            isEnabled = true,
            dhcCheckStyle = DhcCheckStyle.SnackBar,
        )
    },
) {
    Row(
        modifier = modifier
            .background(color = SurfaceColor.neutral500)
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        snackBarActionIcon()
        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = snackBarMessage,
            style = DhcTypoTokens.Body4,
            color = Color.White,
        )
    }
}

@Composable
@Preview
fun PreviewSnackBar() {
    DhcTheme {
        SnackBarContent(
            snackBarMessage = "This is a snackbar",
        )
    }
}