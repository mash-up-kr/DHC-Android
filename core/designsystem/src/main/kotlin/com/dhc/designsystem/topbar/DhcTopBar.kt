package com.dhc.designsystem.topbar

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
        modifier = modifier,
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

            is DhcTopBarState.None -> {
                // Unit 으로 설정하면 Basic -> None 으로 갈 때 scaleOut 으로 사라지는 이슈가 있다.
                Spacer(modifier = Modifier.fillMaxWidth())
            }
        }
    }
}
