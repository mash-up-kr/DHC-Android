package com.dhc.mypage.ui

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dhc.designsystem.DhcTheme
import com.dhc.designsystem.DhcTypoTokens
import com.dhc.designsystem.GradientColor.backgroundGradient01
import com.dhc.designsystem.LocalDhcColors
import com.dhc.designsystem.SurfaceColor
import com.dhc.designsystem.category.DhcCategoryItem
import com.dhc.mypage.MyPageContract.Event
import com.dhc.mypage.MyPageContract.State
import com.dhc.mypage.R
import com.dhc.mypage.model.MissionCategoryUiModel
import com.dhc.mypage.model.MyInfoUiModel
import com.dhc.mypage.ui.settinglist.SettingItem
import com.dhc.mypage.ui.settinglist.SettingList
import com.dhc.presentation.mvi.EventHandler
import com.dhc.designsystem.R as DR

@Composable
fun MyPageScreen(
    state: State,
    eventHandler: EventHandler<Event>,
    modifier: Modifier = Modifier,
    scrollState: ScrollState = rememberScrollState(),
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

        MyInfo(
            myInfoUiModel = state.myInfo,
            modifier = Modifier.fillMaxWidth(),
        )

        SelectedCategory(
            categoryList = state.missionCategories,
            modifier = Modifier.fillMaxWidth()
        )

        HorizontalDivider(
            thickness = 7.dp,
            color = colors.background.backgroundGlassEffect,
        )

        Setting(
            state = state,
            eventHandler = eventHandler,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Composable
private fun MyInfo(
    myInfoUiModel: MyInfoUiModel,
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
            SajuCard(
                sajuName = myInfoUiModel.sajuName,
                animalImageUrl = myInfoUiModel.animalImageUrl,
                modifier = Modifier
                    .height(120.dp)
                    .widthIn(min = 180.dp)
            )
            DateAndTimeOfBirthInfo(
                birthDateTime = myInfoUiModel.birthDateTime,
                modifier = Modifier.padding(top = 16.dp),
            )
        }
    }
}

@Composable
private fun SelectedCategory(
    categoryList: List<MissionCategoryUiModel>,
    modifier: Modifier = Modifier
) {
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
            items(categoryList) { category ->
                DhcCategoryItem(
                    modifier = Modifier.width(120.dp),
                    name = category.displayName,
                    isChecked = false,
                    imageUrl = category.imageUrl,
                )
            }
        }
    }
}

@Composable
private fun Setting(
    state: State,
    eventHandler: EventHandler<Event>,
    modifier: Modifier = Modifier
) {
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
                    onClick = { eventHandler(Event.ClickAppResetButton) },
                ),
            )
        )
        UserIdSection(
            userId = state.userId,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 8.dp),
        )
    }
}

@Composable
fun UserIdSection(
    userId: String,
    modifier: Modifier = Modifier,
) {
    val activity = LocalActivity.current
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .wrapContentSize()
                .clickable {
                    (activity?.getSystemService(Context.CLIPBOARD_SERVICE) as? ClipboardManager)?.let { clipboard ->
                        clipboard.setPrimaryClip(
                            ClipData.newPlainText("label", userId)
                        )
                    }
                },
        ) {
            Text(
                text = stringResource(R.string.user_id_text, userId.trim()),
                style = DhcTypoTokens.Body6,
                color = SurfaceColor.neutral300,
                modifier = Modifier.weight(weight = 1f, fill = false),
            )
            Image(
                painter = painterResource(R.drawable.copy),
                contentDescription = "copy",
            )
        }
    }
}

@Preview
@Composable
private fun MyPageScreenPreview() {
    DhcTheme {
        MyPageScreen(
            state = State(),
            eventHandler = {},
        )
    }
}
