package com.dhc.home.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dhc.common.FullRoundedCornerShape
import com.dhc.designsystem.DhcTheme
import com.dhc.designsystem.DhcTypoTokens
import com.dhc.designsystem.R
import com.dhc.designsystem.SurfaceColor

@Composable
fun MoreButton(
    onClickMoreButton: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.wrapContentSize()
            .clip(FullRoundedCornerShape)
            .clickable { onClickMoreButton() }
            .background(SurfaceColor.neutral700, FullRoundedCornerShape)
            .padding(start = 12.dp, end = 8.dp, top = 4.dp, bottom = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.more),
            style = DhcTypoTokens.Body6,
            color = SurfaceColor.neutral300,
        )
        Spacer(modifier = Modifier.width(4.dp))
        Icon(
            modifier = Modifier.size(20.dp),
            painter = painterResource(R.drawable.ico_arrow_right),
            contentDescription = "MoreButton",
            tint = SurfaceColor.neutral300,
        )
    }
}

@Composable
@Preview
fun PreviewMoreButton() {
    DhcTheme {
        MoreButton(
            onClickMoreButton = {}
        )
    }
}