package com.dhc.reward

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dhc.designsystem.snackbar.DhcSnackBar
import com.dhc.designsystem.snackbar.SnackBarContent
import com.dhc.designsystem.snackbar.showImmediately
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun RewardRoute(
    modifier: Modifier = Modifier,
    navigateToYearFortune: (isSampleData: Boolean) -> Unit = {},
    viewModel: RewardViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collectLatest { sideEffect ->
            when (sideEffect) {
                is RewardContract.SideEffect.NavigateToYearFortune -> {
                    navigateToYearFortune(sideEffect.isSampleData)
                }
                is RewardContract.SideEffect.ShowToast -> {
                    scope.launch {
                        snackBarHostState.showImmediately(sideEffect.msg)
                    }
                }
            }
        }
    }

    Box(modifier = modifier.fillMaxSize()) {
        RewardScreen(
            modifier = Modifier.fillMaxSize(),
            state = state,
            onEvent = viewModel::sendEvent,
            navigateToYearFortune = {}
        )

        DhcSnackBar(
            modifier = Modifier.align(Alignment.BottomCenter),
            snackBarHostState = snackBarHostState,
            snackBarContent = {
                SnackBarContent(
                    snackBarMessage = snackBarHostState.currentSnackbarData?.visuals?.message.orEmpty(),
                    snackBarActionIcon = {
                        Image(
                            modifier = Modifier.size(20.dp),
                            painter = painterResource(com.dhc.designsystem.R.drawable.ico_present),
                            contentDescription = null,
                        )
                    }
                )
            }
        )
    }
}
