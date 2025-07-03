package com.dhc.intro.description

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dhc.designsystem.DhcTheme
import com.dhc.designsystem.DhcTypoTokens
import com.dhc.designsystem.button.DhcButton
import com.dhc.designsystem.button.model.DhcButtonSize
import com.dhc.designsystem.button.model.DhcButtonStyle
import com.dhc.designsystem.info.DhcInfo
import com.dhc.designsystem.title.DhcTitle
import com.dhc.designsystem.title.DhcTitleState
import com.dhc.intro.R
import com.dhc.presentation.mvi.EventHandler

@Composable
fun IntroDescriptionScreen(
    eventHandler: EventHandler<IntroDescriptionContract.Event>,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        Column(modifier = Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier.height(48.dp))
            DhcTitle(
                titleState = DhcTitleState(
                    title = stringResource(R.string.intro_description_title),
                    titleStyle = DhcTypoTokens.TitleH2,
                ),
                textAlign = TextAlign.Center,
                subTitleState = DhcTitleState(
                    title = stringResource(R.string.intro_description_sub_title),
                    titleStyle = DhcTypoTokens.Body3,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp, start = 20.dp, end = 20.dp),
            )
            Spacer(modifier = Modifier.height(56.dp))
            Column(
                modifier = Modifier.padding(top = 24.dp, start = 20.dp, end = 20.dp, bottom = 32.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                listOf(
                    R.string.intro_description_text1,
                    R.string.intro_description_text2,
                    R.string.intro_description_text3,
                ).forEachIndexed { index, stringRes ->
                    DhcInfo(
                        no = index + 1,
                        text = stringResource(stringRes),
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
            }
        }
        DhcButton(
            text = stringResource(R.string.next),
            buttonSize = DhcButtonSize.XLARGE,
            buttonStyle = DhcButtonStyle.Secondary(isEnabled = true),
            onClick = { eventHandler(IntroDescriptionContract.Event.ClickNextButton) },
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun IntroDescriptionScreenPreview() {
    DhcTheme {
        IntroDescriptionScreen(eventHandler = {})
    }
}
