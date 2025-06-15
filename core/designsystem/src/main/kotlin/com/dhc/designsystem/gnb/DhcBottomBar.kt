package com.dhc.designsystem.gnb

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.dhc.designsystem.gnb.model.DhcBottomBarState

@Composable
fun DhcBottomBar(
    state: DhcBottomBarState,
    navigateToRoute: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    when (state) {
        is DhcBottomBarState.BottomNavigation -> {
            var selectedIndex by remember { mutableIntStateOf(0) }
            DhcGnb(
                gnbItemList = DhcBottomBarState.BottomNavigation.items,
                selectedIndex = selectedIndex,
                onClickItem = { gnbItem, index ->
                    navigateToRoute(gnbItem.routeName)
                    selectedIndex = index
                },
                modifier = modifier,
            )
        }

        is DhcBottomBarState.None -> Unit
    }
}
