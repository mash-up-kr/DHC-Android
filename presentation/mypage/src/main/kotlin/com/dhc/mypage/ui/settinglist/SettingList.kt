package com.dhc.mypage.ui.settinglist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dhc.designsystem.DhcTheme
import com.dhc.designsystem.LocalDhcColors
import com.dhc.designsystem.SurfaceColor
import com.dhc.designsystem.R as DR

@Composable
internal fun SettingList(
    settingItems: List<SettingItem>,
    modifier: Modifier = Modifier
) {
    val colors = LocalDhcColors.current

    Column(
        modifier = modifier.background(color = SurfaceColor.neutral700, shape = RoundedCornerShape(12.dp)),
    ) {
        settingItems.forEachIndexed { index, settingItem ->
            when (settingItem) {
                is SettingItem.Normal -> {
                    SettingNormalItem(
                        item = settingItem,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { settingItem.onClick() }
                            .padding(all = 16.dp),
                    )
                }
                is SettingItem.Toggle -> {
                    SettingToggleItem(
                        item = settingItem,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(all = 16.dp),
                    )
                }
            }
            if (index < settingItems.lastIndex) {
                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth(),
                    thickness = 1.dp,
                    color = colors.background.backgroundGlassEffect,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SettingListPreview() {
    DhcTheme {
        SettingList(
            settingItems = listOf(
                SettingItem.Normal(text = "내 정보", iconRes = DR.drawable.ico_sign_out),
                SettingItem.Toggle(text = "알림 설정", iconRes = DR.drawable.ico_sign_out, isOn = true),
                SettingItem.Normal(text = "고객센터", iconRes = DR.drawable.ico_sign_out),
                SettingItem.Normal(text = "약관 및 정책", iconRes = DR.drawable.ico_sign_out),
                SettingItem.Normal(text = "로그아웃", iconRes = DR.drawable.ico_sign_out),
            )
        )
    }
}
