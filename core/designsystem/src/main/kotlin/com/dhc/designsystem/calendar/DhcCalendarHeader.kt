package com.dhc.designsystem.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun DhcCalendarHeader(
    currentDate: LocalDate,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(color = Color.Gray)
                .clickable { },
        )
        Text(
            text = currentDate.format(DateTimeFormatter.ofPattern("yyyy년 MM월")),
        )
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(color = Color.Gray)
                .clickable { },
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DhcCalendarHeaderPreview() {
    DhcCalendarHeader(
        currentDate = LocalDate.now(),
        modifier = Modifier.fillMaxWidth(),
    )
}
