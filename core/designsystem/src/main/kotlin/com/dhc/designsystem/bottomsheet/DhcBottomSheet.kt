package com.dhc.designsystem.bottomsheet

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dhc.designsystem.LocalDhcColors
import com.dhc.designsystem.R
import com.dhc.designsystem.SurfaceColor
import kotlinx.coroutines.launch

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun DhcModalBottomSheet(
    isCloseButtonEnabled: Boolean,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    containerColor: Color = SurfaceColor.neutral700,
    content: @Composable ColumnScope.() -> Unit,
) {
    val sheetState = rememberModalBottomSheetState(
        confirmValueChange = { newValue ->
            !isCloseButtonEnabled || newValue != SheetValue.Hidden
        }
    )

    val scope = rememberCoroutineScope()

    ModalBottomSheet(
        sheetState = sheetState,
        modifier = modifier,
        dragHandle = {},
        containerColor = Color.Transparent,
        onDismissRequest = {
            if (!isCloseButtonEnabled) {
                onDismissRequest()
            }
        },
    ) {
        Surface(
            modifier = Modifier,
            shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
            color = containerColor,
            border = BorderStroke(1.dp, SurfaceColor.neutral600),
        ) {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                if(isCloseButtonEnabled) {
                    IconButton (
                        modifier = Modifier.align(Alignment.End),
                        onClick = {
                            scope.launch { sheetState.hide() }
                            onDismissRequest()
                        }
                    ) {
                        Image(
                            painter = painterResource(R.drawable.ico_x),
                            contentDescription = "closeButton"
                        )
                    }
                }
                content()
            }
        }
    }
}


@Preview
@Composable
private fun PreviewDhcModalBottomSheet() {
    val colors = LocalDhcColors.current
    DhcModalBottomSheet(
        isCloseButtonEnabled = false,
        containerColor = colors.background.backgroundMain,
        onDismissRequest = {},
        content = {}
    )
}
