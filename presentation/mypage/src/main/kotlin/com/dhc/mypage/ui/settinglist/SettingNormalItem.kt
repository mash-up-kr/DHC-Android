package com.dhc.mypage.ui.settinglist

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dhc.designsystem.DhcTheme
import com.dhc.designsystem.DhcTypoTokens
import com.dhc.designsystem.LocalDhcColors
import com.dhc.designsystem.R as DR

@Composable
internal fun SettingNormalItem(
    item: SettingItem.Normal,
    modifier: Modifier = Modifier,
) {
    val colors = LocalDhcColors.current

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Image(
            painter = painterResource(item.iconRes),
            contentDescription = "sign out",
        )
        Text(
            modifier = Modifier.weight(1f),
            text = item.text,
            style = DhcTypoTokens.Body3,
            color = colors.text.textMain,
        )
    }
}

@Preview
@Composable
private fun SettingNormalItemPreview() {
    DhcTheme {
        SettingNormalItem(
            item = SettingItem.Normal(
                text = "로그아웃",
                iconRes = DR.drawable.ico_sign_out,
            ),
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        )
    }
}
