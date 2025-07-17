package com.dhc.designsystem.gnb

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
        .coerceAtLeast(0)

    AnimatedContent(
        targetState = state,
        transitionSpec = {
            slideInVertically(
                initialOffsetY = { it }
            ) togetherWith slideOutVertically(
                targetOffsetY = { it }
            )
        },
        contentKey = { currentState -> currentState::class.java.simpleName },
        modifier = modifier,
    ) { currentState ->
        when (currentState) {
            is DhcBottomBarState.BottomNavigation -> {
                DhcGnb(
                    gnbItemList = items,
                    selectedIndex = selectedIndex,
                    onClickItem = { gnbItem, _ ->
                        navigateToRoute(gnbItem.routeName)
                    },
                    modifier = Modifier.height(56.dp),
                )
            }

            is DhcBottomBarState.None -> Unit
        }
    }
}
