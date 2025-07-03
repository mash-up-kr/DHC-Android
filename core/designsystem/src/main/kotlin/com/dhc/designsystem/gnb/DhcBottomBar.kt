package com.dhc.designsystem.gnb

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.dhc.designsystem.gnb.model.DhcBottomBarState

@Composable
fun DhcBottomBar(
    state: DhcBottomBarState,
    currentRoute: String,
    navigateToRoute: (String) -> Unit,
    modifier: Modifier = Modifier,
) {

    val items = DhcBottomBarState.BottomNavigation.items
    val selectedIndex = items
        .indexOfFirst { it.routeName == currentRoute }
        .takeIf { it >= 0 } ?: 0

    when (state) {
        is DhcBottomBarState.BottomNavigation -> {
            DhcGnb(
                gnbItemList = items,
                selectedIndex = selectedIndex,
                onClickItem = { gnbItem, index ->
                    navigateToRoute(gnbItem.routeName)
                },
                modifier = modifier,
            )
        }

        is DhcBottomBarState.None -> Unit
    }
}
