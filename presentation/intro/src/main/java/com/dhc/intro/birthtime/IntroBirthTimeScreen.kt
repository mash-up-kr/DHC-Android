package com.dhc.intro.birthtime

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.dhc.designsystem.checkbutton.DhcCheckButton
import com.dhc.designsystem.spinner.DhcTimeSpinner
import com.dhc.designsystem.title.DhcTitle
import com.dhc.designsystem.title.DhcTitleState
import com.dhc.intro.R
import com.dhc.presentation.mvi.EventHandler

@Composable
fun IntroBirthTimeScreen(
    state: IntroBirthTimeContract.State,
    eventHandler: EventHandler<IntroBirthTimeContract.Event>,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        Column(modifier = Modifier.fillMaxSize()) {
            DhcTitle(
                titleState = DhcTitleState(
                    title = stringResource(R.string.intro_birth_time_title),
                    titleStyle = DhcTypoTokens.TitleH1,
                ),
                textAlign = TextAlign.Start,
                subTitleState = DhcTitleState(
                    title = stringResource(R.string.intro_birth_time_sub_title),
                    titleStyle = DhcTypoTokens.Body3,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp, start = 20.dp, end = 20.dp),
            )
            Spacer(modifier = Modifier.height(84.dp))
            IdkSelector(
                isChecked = state.isIdkChecked,
                onClickButton = { changedIsCheck ->
                    eventHandler(IntroBirthTimeContract.Event.SelectIdkButton(changedIsCheck))
                },
            )
            Spacer(modifier = Modifier.height(13.dp))
            DhcTimeSpinner(
                onValueChanged = { timeType, time, minute ->
                    eventHandler(IntroBirthTimeContract.Event.SelectBirthTime(timeType, time, minute))
                },
                modifier = Modifier.fillMaxWidth(),
                initialTimeType = state.timeType,
                initialTime = state.time,
                initialMinute = state.minute,
                isEnabled = state.isIdkChecked.not(),
            )
        }
        DhcButton(
            text = stringResource(R.string.next),
            buttonSize = DhcButtonSize.XLARGE,
            buttonStyle = DhcButtonStyle.Secondary(isEnabled = true),
            onClick = { eventHandler(IntroBirthTimeContract.Event.ClickNextButton) },
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
        )
    }
}

@Composable
private fun IdkSelector(
    isChecked: Boolean,
    onClickButton: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        val colors = LocalDhcColors.current
        Text(
            text = stringResource(R.string.birth_time),
            style = TextStyle(
                fontFamily = typoFontFamily,
                fontWeight = FontWeight.W500,
                fontSize = 14.sp,
                lineHeight = 20.sp,
            ),
            color = colors.text.textBodyPrimary,
        )
        DhcCheckButton(
            text = stringResource(R.string.idk),
            isChecked = isChecked,
            modifier = Modifier.clickable { onClickButton(isChecked.not()) }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun IntroBirthTimeScreenPreview() {
    DhcTheme {
        IntroBirthTimeScreen(
            state = IntroBirthTimeContract.State(),
            eventHandler = {},
        )
    }
}
