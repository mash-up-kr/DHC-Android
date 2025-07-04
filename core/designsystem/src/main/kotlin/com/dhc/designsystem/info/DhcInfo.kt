package com.dhc.designsystem.info

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dhc.designsystem.DhcTheme
import com.dhc.designsystem.DhcTypoTokens
import com.dhc.designsystem.LocalDhcColors
import com.dhc.designsystem.SurfaceColor

@Composable
fun DhcInfo(
    no: Int,
    text: String,
    modifier: Modifier = Modifier,
) {
    val colors = LocalDhcColors.current
    Row(
        modifier = modifier
            .background(color = SurfaceColor.neutral700, shape = RoundedCornerShape(12.dp))
            .padding(20.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(24.dp)
                .background(color = SurfaceColor.neutral300, shape = CircleShape),
        ) {
            Text(
                text = no.toString(),
                color = colors.background.backgroundMain,
                style = DhcTypoTokens.TitleH5,
                modifier = Modifier.align(Alignment.Center),
            )
        }
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = text,
            color = colors.text.textBodyPrimary,
            style = DhcTypoTokens.TitleH4_1,
        )
    }
}

@Preview
@Composable
private fun DhcInfoPreview() {
    DhcTheme {
        DhcInfo(
            no = 1,
            text = "오늘의 금전운 받기",
        )
    }
}
