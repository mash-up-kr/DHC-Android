package com.dhc.designsystem.gnb

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.dhc.designsystem.gnb.model.DhcBottomBarState

@Composable
fun DhcBottomBar(
    state: DhcBottomBarState,
    selectedIndex: Int,
    navigateToRoute: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    when (state) {
        is DhcBottomBarState.BottomNavigation -> {
            DhcGnb(
                gnbItemList = DhcBottomBarState.BottomNavigation.items,
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
