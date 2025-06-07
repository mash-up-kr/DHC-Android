package com.dhc.designsystem.calendar

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
) {
    val colors = LocalDhcColors.current

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            modifier = Modifier
                .padding(4.dp)
                .weight(1f),
            painter = painterResource(R.drawable.ico_arrow_left),
            tint = colors.text.textMain,
            contentDescription = null,
        )
        Text(
            modifier = Modifier.weight(5f),
            text = currentDate.format(DateTimeFormatter.ofPattern("yyyy년 MM월")),
            textAlign = TextAlign.Center,
            color = colors.text.textMain,
            style = Body3,
        )
        Icon(
            modifier = Modifier
                .padding(4.dp)
                .weight(1f),
            painter = painterResource(R.drawable.ico_arrow_right),
            tint = colors.text.textMain,
            contentDescription = null,
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
