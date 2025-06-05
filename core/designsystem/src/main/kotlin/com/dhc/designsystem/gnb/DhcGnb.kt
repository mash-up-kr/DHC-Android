package com.dhc.designsystem.gnb

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dhc.designsystem.DHCAndroidTheme
import com.dhc.designsystem.R
import com.dhc.designsystem.gnb.model.GnbItem

@Composable
fun DhcGnb(
    gnbItemList: List<GnbItem>,
    selectedIndex: Int,
    onClickItem: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        gnbItemList.forEachIndexed { index, item ->
            DhcBtnBottom(
                gnbItem = item,
                isSelected = index == selectedIndex,
                onClick = onClickItem,
                modifier = Modifier.size(width = 52.dp, height = 43.dp),
            )
        }
    }
}

@Preview
@Composable
private fun DhcGnbPreview() {
    DHCAndroidTheme {
        DhcGnb(
            gnbItemList = listOf(
                GnbItem(
                    iconResource = R.drawable.home_04,
                    iconText = R.string.btn_bottom_home,
                    routeName = "MAIN_HOME",
                ),
                GnbItem(
                    iconResource = R.drawable.bar_chart_10,
                    iconText = R.string.btn_bottom_mission,
                    routeName = "MAIN_MISSION",
                ),
                GnbItem(
                    iconResource = R.drawable.user_02,
                    iconText = R.string.btn_bottom_my_page,
                    routeName = "MAIN_MY",
                ),
            ),
            selectedIndex = 0,
            onClickItem = {},
        )
    }
}
