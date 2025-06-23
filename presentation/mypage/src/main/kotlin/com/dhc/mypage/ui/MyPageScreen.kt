package com.dhc.mypage.ui

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dhc.designsystem.DhcTheme
import com.dhc.designsystem.DhcTypoTokens
import com.dhc.designsystem.GradientColor.backgroundGradient01
import com.dhc.designsystem.LocalDhcColors
import com.dhc.designsystem.SurfaceColor
import com.dhc.designsystem.category.DhcCategoryItem
import com.dhc.mypage.MyPageContract.State
import com.dhc.mypage.R
import com.dhc.mypage.ui.settinglist.SettingItem
import com.dhc.mypage.ui.settinglist.SettingList
import com.dhc.designsystem.R as DR

@Composable
fun MyPageScreen(
    state: State,
    modifier: Modifier = Modifier,
    scrollState: ScrollState = rememberScrollState()
) {
    val colors = LocalDhcColors.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(color = SurfaceColor.neutral800) // Todo :: 배경색 변경 필요
            .padding(vertical = 20.dp)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp, horizontal = 20.dp),
            text = stringResource(R.string.mypage_screen_title),
            style = DhcTypoTokens.TitleH2_1,
            color = colors.text.textBodyPrimary,
        )

        MyInfo(modifier = Modifier.fillMaxWidth())

        SelectedCategory(modifier = Modifier.fillMaxWidth())

        HorizontalDivider(
            thickness = 7.dp,
            color = colors.background.backgroundGlassEffect,
        )

        Setting(modifier = Modifier.fillMaxWidth())
    }
}

@Composable
private fun MyInfo(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.background(brush = backgroundGradient01)
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 20.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            SajuCard(modifier = Modifier.size(width = 180.dp, height = 120.dp))
            DateAndTimeOfBirthInfo(modifier = Modifier.padding(top = 16.dp))
        }
    }
}

@Composable
private fun SelectedCategory(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Text(
            modifier = Modifier.padding(start = 20.dp),
            text = stringResource(R.string.selected_consumption_category),
            style = DhcTypoTokens.Body3,
            color = SurfaceColor.neutral30,
        )

        LazyRow(
            contentPadding = PaddingValues(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(5) {
                DhcCategoryItem(
                    modifier = Modifier.width(120.dp),
                    name = "식음료",
                    isChecked = false,
                    iconResource = DR.drawable.ico_check,
                )
            }
        }
    }
}

@Composable
private fun Setting(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Text(
            modifier = Modifier.padding(start = 20.dp),
            text = stringResource(R.string.setting),
            style = DhcTypoTokens.Body3,
            color = SurfaceColor.neutral30,
        )
        SettingList(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            settingItems = listOf(
                SettingItem.Normal(
                    text = stringResource(R.string.initial_app),
                    iconRes = DR.drawable.ico_sign_out,
                    onClick = {},
                ),
                SettingItem.Toggle(
                    text = stringResource(R.string.setting_notification),
                    iconRes = DR.drawable.ico_sign_out,
                    isOn = true,
                    onCheckedChange = {},
                ),
            )
        )
    }
}

@Preview
@Composable
private fun MyPageScreenPreview() {
    DhcTheme {
        MyPageScreen(
            state = State(),
        )
    }
}
