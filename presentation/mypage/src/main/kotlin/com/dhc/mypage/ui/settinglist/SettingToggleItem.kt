package com.dhc.mypage.ui.settinglist

import android.R.attr.text
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.dhc.common.ImageResource
import com.dhc.designsystem.DhcTheme
import com.dhc.designsystem.DhcTypoTokens
import com.dhc.designsystem.LocalDhcColors
import com.dhc.designsystem.switch.DhcSwitch
import com.dhc.designsystem.R as DR

@Composable
internal fun SettingToggleItem(
    item: SettingItem.Toggle,
    modifier: Modifier = Modifier,
) {
    val colors = LocalDhcColors.current

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        when (item.imageResource) {
            is ImageResource.Drawable -> {
                Image(
                    painter = painterResource(item.imageResource.resId),
                    contentDescription = "",
                )
            }
            is ImageResource.Url -> {
                AsyncImage(
                    model = item.imageResource.url,
                    contentDescription = "",
                    modifier = Modifier.size(20.dp),
                )
            }
        }
        Text(
            modifier = Modifier.weight(1f),
            text = item.text,
            style = DhcTypoTokens.Body3,
            color = colors.text.textMain,
        )
        DhcSwitch(
            isOn = item.isOn,
            onToggle = item.onCheckedChange,
        )
    }
}

@Preview
@Composable
private fun SettingToggleItemPreview() {
    DhcTheme {
        SettingToggleItem(
            item = SettingItem.Toggle(
                text = "알림 설정",
                imageResource = ImageResource.Drawable(DR.drawable.ico_sign_out),
                isOn = true,
                onCheckedChange = {},
            ),
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        )
    }
}
