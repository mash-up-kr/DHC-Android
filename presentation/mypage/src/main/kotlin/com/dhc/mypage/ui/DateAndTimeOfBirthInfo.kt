package com.dhc.mypage.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dhc.common.FormatterUtil.dhcDateFormat
import com.dhc.common.FormatterUtil.dhcTimeFormat
import com.dhc.common.FullRoundedCornerShape
import com.dhc.designsystem.DhcTheme
import com.dhc.designsystem.DhcTypoTokens
import com.dhc.designsystem.LocalDhcColors
import java.time.LocalDateTime

@Composable
fun DateAndTimeOfBirthInfo(
    birthDateTime: LocalDateTime?,
    modifier: Modifier = Modifier
) {
    val colors = LocalDhcColors.current

    Row(
        modifier = modifier
            .background(
                color = colors.background.backgroundGlassEffect,
                shape = FullRoundedCornerShape,
            )
            .padding(vertical = 4.dp, horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text (
            text = birthDateTime?.format(dhcDateFormat).orEmpty(),
            textAlign = TextAlign.End,
            style = DhcTypoTokens.Body6,
            color = colors.text.textBodyPrimary,
        )
        Box(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .size(width = 1.dp, height = 12.dp)
                .background(colors.background.backgroundGlassEffect),
        )
        Text (
            text = birthDateTime?.format(dhcTimeFormat).orEmpty(),
            textAlign = TextAlign.Start,
            style = DhcTypoTokens.Body6,
            color = colors.text.textBodyPrimary,
        )
    }
}

@Preview
@Composable
private fun DateAndTimeOfBirthInfoPreview() {
    DhcTheme {
        DateAndTimeOfBirthInfo(
            birthDateTime = LocalDateTime.of(2023, 10, 1, 12, 30)
        )
    }
}
