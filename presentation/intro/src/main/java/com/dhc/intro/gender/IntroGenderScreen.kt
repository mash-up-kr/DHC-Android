package com.dhc.intro.gender

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dhc.designsystem.DhcTheme
import com.dhc.designsystem.DhcTypoTokens
import com.dhc.designsystem.DhcTypoTokens.typoFontFamily
import com.dhc.designsystem.LocalDhcColors
import com.dhc.designsystem.button.DhcButton
import com.dhc.designsystem.button.model.DhcButtonSize
import com.dhc.designsystem.button.model.DhcButtonStyle
import com.dhc.designsystem.title.DhcTitle
import com.dhc.designsystem.title.DhcTitleState
import com.dhc.intro.R
import com.dhc.intro.model.Gender
import com.dhc.presentation.mvi.EventHandler

@Composable
fun IntroGenderScreen(
    state: IntroGenderContract.State,
    eventHandler: EventHandler<IntroGenderContract.Event>,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            DhcTitle(
                titleState = DhcTitleState(
                    title = stringResource(R.string.intro_gender_title),
                    titleStyle = DhcTypoTokens.TitleH2,
                ),
                textAlign = TextAlign.Start,
                subTitleState = DhcTitleState(
                    title = stringResource(R.string.intro_gender_sub_title),
                    titleStyle = DhcTypoTokens.Body3,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp, start = 20.dp, end = 20.dp),
            )
            Spacer(modifier = Modifier.height(84.dp))
            GenderSelector(
                selectedGender = state.gender,
                selectGender = { gender ->
                    eventHandler(IntroGenderContract.Event.SelectGender(gender))
                },
            )
        }
        DhcButton(
            text = stringResource(R.string.next),
            buttonSize = DhcButtonSize.XLARGE,
            buttonStyle = DhcButtonStyle.Secondary(isEnabled = state.gender != null),
            onClick = { eventHandler(IntroGenderContract.Event.ClickNextButton) },
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
        )
    }
}

@Composable
fun GenderSelector(
    selectedGender: Gender?,
    selectGender: (Gender) -> Unit,
) {
    val colors = LocalDhcColors.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
    ) {
        Text(
            text = stringResource(R.string.gender),
            style = TextStyle(
                fontFamily = typoFontFamily,
                fontWeight = FontWeight.W500,
                fontSize = 14.sp,
                lineHeight = 20.sp,
            ),
            color = colors.text.textBodyPrimary,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Gender.entries.forEach { gender ->
                DhcButton(
                    text = stringResource(gender.textResourceId),
                    buttonSize = DhcButtonSize.LARGE,
                    buttonStyle = if (selectedGender == gender) {
                        DhcButtonStyle.Primary(isEnabled = true)
                    } else {
                        DhcButtonStyle.Secondary(isEnabled = true)
                    },
                    onClick = { selectGender(gender) },
                    modifier = Modifier.weight(1f),
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun IntroGenderScreenPreview() {
    DhcTheme {
        IntroGenderScreen(
            state = IntroGenderContract.State(),
            eventHandler = {},
        )
    }
}
