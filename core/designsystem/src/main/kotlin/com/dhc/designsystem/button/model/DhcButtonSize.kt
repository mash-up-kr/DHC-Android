package com.dhc.designsystem.button.model

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dhc.designsystem.DhcTypoTokens

enum class DhcButtonSize(val textStyle: TextStyle, val verticalPadding: Dp) {
    LARGE(
        textStyle = DhcTypoTokens.TitleH5, // Todo : H5-1 로 변경
        verticalPadding = 15.dp,
    ),
    MIDDLE(
        textStyle = DhcTypoTokens.TitleH7,
        verticalPadding = 12.dp,
    ),
    ;
}
