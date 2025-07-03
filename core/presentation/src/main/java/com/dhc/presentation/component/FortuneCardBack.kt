package com.dhc.presentation.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dhc.designsystem.DhcTheme
import com.dhc.designsystem.fortunecard.DhcFortuneCard
import com.dhc.presentation.R

@Composable
fun FortuneCardBack(modifier: Modifier = Modifier) {
    DhcFortuneCard(
        title = "",
        description = "",
        cardDrawableResId = R.drawable.fortune_card_back,
        modifier = modifier,
    )
}

@Preview
@Composable
private fun FortuneCardBackPreview() {
    DhcTheme {
        FortuneCardBack()
    }
}
