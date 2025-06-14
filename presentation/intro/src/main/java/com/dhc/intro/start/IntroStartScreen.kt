package com.dhc.intro.start

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dhc.designsystem.DhcTheme
import com.dhc.designsystem.button.DhcButton
import com.dhc.designsystem.button.model.DhcButtonSize
import com.dhc.designsystem.button.model.DhcButtonStyle
import com.dhc.designsystem.title.DhcTitle
import com.dhc.intro.R
import com.dhc.presentation.mvi.EventHandler

@Composable
fun IntroStartScreen(
    eventHandler: EventHandler<IntroStartContract.Event>,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        Column(modifier = modifier) {
            Spacer(modifier = Modifier.height(24.dp))
            DhcTitle(
                title = stringResource(R.string.intro_start_title),
                textAlign = TextAlign.Center,
                subTitle = stringResource(R.string.intro_start_sub_title),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp, start = 20.dp, end = 20.dp),
            )
            Spacer(modifier = Modifier.height(38.dp))
            // Todo : 그래픽 추가예정
        }
        DhcButton(
            text = stringResource(R.string.start_with_finance_luck),
            buttonSize = DhcButtonSize.XLARGE,
            buttonStyle = DhcButtonStyle.Secondary(isEnabled = true),
            onClick = { eventHandler(IntroStartContract.Event.ClickNextButton) },
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun IntroStartScreenPreview() {
    DhcTheme {
        IntroStartScreen(eventHandler = {})
    }
}
