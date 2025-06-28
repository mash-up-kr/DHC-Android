package com.dhc.home.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dhc.designsystem.DhcTheme
import com.dhc.designsystem.DhcTypoTokens
import com.dhc.designsystem.LocalDhcColors

@Composable
fun MonetaryLuckInfo(
    fortuneDetail: String,
    fortuneScore: Int,
    onClickMoreButton: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = LocalDhcColors.current

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = fortuneDetail,
                style = DhcTypoTokens.TitleH2_1,
                color = colors.text.textBodyPrimary
            )
            Spacer(modifier = Modifier.height(12.dp))
            MoreButton(
                onClickMoreButton = { onClickMoreButton() }
            )
        }
        FortuneCore(
            modifier = Modifier.size(75.dp),
            fortuneScore = fortuneScore
        )
    }
}

@Composable
@Preview
fun PreviewMonetaryLuckInfo() {
    DhcTheme {
        MonetaryLuckInfo(
            onClickMoreButton = {},
            fortuneScore = 35,
            fortuneDetail = "지갑이 들뜨는 날,\n한템포 쉬어가요."
        )
    }
}