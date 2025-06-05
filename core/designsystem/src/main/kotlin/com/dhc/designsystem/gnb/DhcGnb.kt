package com.dhc.designsystem.gnb

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dhc.designsystem.DhcTheme
import com.dhc.designsystem.DhcTypoTokens
import com.dhc.designsystem.LocalDhcColors
import com.dhc.designsystem.R
import com.dhc.designsystem.SurfaceColor
import com.dhc.designsystem.gnb.model.GnbItem

@Composable
fun DhcGnb(
    gnbItemList: List<GnbItem>,
    selectedIndex: Int,
    onClickItem: (gnbItem: GnbItem, index: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = LocalDhcColors.current
    NavigationBar(
        modifier = modifier,
        containerColor = colors.background.backgroundMain,
    ) {
        gnbItemList.forEachIndexed { index, gnbItem ->
            NavigationBarItem(
                selected = index == selectedIndex,
                onClick = { onClickItem(gnbItem, index) },
                icon = {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(gnbItem.iconResource),
                        contentDescription = null,
                    )
                },
                label = {
                    Text(
                        text = stringResource(gnbItem.iconText),
                        style = DhcTypoTokens.Body7
                    )
                },
                colors = NavigationBarItemColors(
                    selectedIconColor = SurfaceColor.neutral200,
                    unselectedIconColor = SurfaceColor.neutral600,
                    selectedTextColor = SurfaceColor.neutral200,
                    unselectedTextColor = SurfaceColor.neutral600,
                    selectedIndicatorColor = Color.Transparent,
                    disabledIconColor = SurfaceColor.neutral600,
                    disabledTextColor = SurfaceColor.neutral600,
                ),
                alwaysShowLabel = true,
            )
        }
    }
}

@Preview
@Composable
private fun DhcGnbPreview() {
    DhcTheme {
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
            onClickItem = { _, _ -> },
        )
    }
}
