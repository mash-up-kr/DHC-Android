package com.dhc.designsystem.calendar.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dhc.designsystem.DhcTheme
import com.dhc.designsystem.DhcTypoTokens.Body3
import com.dhc.designsystem.LocalDhcColors
import com.dhc.designsystem.R
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun DhcCalendarHeader(
    currentDate: LocalDate,
    modifier: Modifier = Modifier,
    onClickLeftButton: () -> Unit = {},
    onClickRightButton: () -> Unit = {},
) {
    val colors = LocalDhcColors.current

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            modifier = Modifier
                .clickable { onClickLeftButton() }
                .padding(4.dp),
            painter = painterResource(R.drawable.ico_arrow_left),
            tint = colors.text.textMain,
            contentDescription = "calendar left button",
        )
        Text(
            text = currentDate.format(DateTimeFormatter.ofPattern("yyyy년 MM월")),
            textAlign = TextAlign.Center,
            color = colors.text.textMain,
            style = Body3,
        )
        Icon(
            modifier = Modifier
                .padding(4.dp)
                .clickable { onClickRightButton() },
            painter = painterResource(R.drawable.ico_arrow_right),
            tint = colors.text.textMain,
            contentDescription = "calendar right button",
        )
    }
}

@Preview
@Composable
private fun DhcCalendarHeaderPreview() {
    DhcTheme {
        DhcCalendarHeader(
            currentDate = LocalDate.now(),
            modifier = Modifier.fillMaxWidth(),
        )
    }
}
