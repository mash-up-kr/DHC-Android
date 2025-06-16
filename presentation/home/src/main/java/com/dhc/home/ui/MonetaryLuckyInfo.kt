package com.dhc.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MonetaryLuckInfo(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = "지갑이 들뜨는 날,\n한템포 쉬어가요."
            )
            Button(
                onClick = {  },
            ) {
                Text(
                    text = "더보기"
                )
            }
        }
        Box(
            modifier = Modifier
                .size(80.dp)
                .background(color = Color.White, shape = RoundedCornerShape(99))
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "금전운"
            )
        }
    }
}

@Composable
@Preview
fun PreviewMonetaryLuckInfo() {
    MonetaryLuckInfo()
}