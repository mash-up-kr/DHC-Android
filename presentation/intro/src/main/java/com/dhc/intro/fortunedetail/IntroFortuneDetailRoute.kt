package com.dhc.intro.fortunedetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dhc.designsystem.GradientColor
import com.dhc.designsystem.GradientColor.buttonGradient
import com.dhc.designsystem.LocalDhcColors
import com.dhc.designsystem.button.DhcButton
import com.dhc.designsystem.button.model.DhcButtonSize
import com.dhc.designsystem.button.model.DhcButtonStyle
import com.dhc.presentation.R
import com.dhc.presentation.ui.monetaryDetail.MonetaryLuckDetailScreen

@Composable
fun IntroFortuneDetailRoute(
    navigateToNextScreen: () -> Unit,
    viewModel: IntroFortuneDetailViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                IntroFortuneDetailContract.SideEffect.NavigateToNextScreen -> navigateToNextScreen()
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        val colors = LocalDhcColors.current

        MonetaryLuckDetailScreen(
            monetaryLuckInfo = state.monetaryLuckInfo,
            modifier = Modifier
                .padding(top = 12.dp)
                .fillMaxWidth(),
        )

        Column(modifier = Modifier.align(Alignment.BottomCenter)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .background(brush = buttonGradient)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(colors.background.backgroundMain)
            ) {
                DhcButton(
                    text = stringResource(R.string.monetary_confirm_and_start),
                    buttonSize = DhcButtonSize.XLARGE,
                    buttonStyle = DhcButtonStyle.Secondary(isEnabled = true),
                    onClick = { viewModel.sendEvent(IntroFortuneDetailContract.Event.ClickNextButton) },
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxWidth()
                        .align(Alignment.Center),
                )
            }
        }
    }
}
