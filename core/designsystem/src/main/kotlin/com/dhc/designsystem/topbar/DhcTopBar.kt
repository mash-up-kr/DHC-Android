package com.dhc.designsystem.topbar

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.dhc.designsystem.topbar.model.DhcTopBarState

@Composable
fun DhcTopBar(
    state: DhcTopBarState,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
) {
    AnimatedContent(
        targetState = state,
        transitionSpec = {
            slideInVertically(
                initialOffsetY = { -it }
            ) togetherWith slideOutVertically(
                targetOffsetY = { -it }
            )
        },
        contentKey = { currentState -> currentState::class.java.simpleName },
    ) { currentState ->
        when (currentState) {
            is DhcTopBarState.Basic -> {
                DhcBasicTopBar(
                    title = currentState.title,
                    isShowBackButton = currentState.isShowBackButton,
                    modifier = modifier,
                    topBarPageState = currentState.topBarPageState,
                    onClickBackButton = navigateUp,
                )
            }

            is DhcTopBarState.None -> Unit
        }
    }
}
