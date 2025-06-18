package com.dhc.intro.birthday

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
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
import com.dhc.designsystem.info.DhcInfo
import com.dhc.designsystem.spinner.DhcDaySpinner
import com.dhc.designsystem.title.DhcTitle
import com.dhc.designsystem.title.DhcTitleState
import com.dhc.intro.R
import com.dhc.intro.model.CalendarType
import com.dhc.presentation.mvi.EventHandler

@Composable
fun IntroBirthDayScreen(
    state: IntroBirthDayContract.State,
    eventHandler: EventHandler<IntroBirthDayContract.Event>,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        Column(modifier = Modifier.fillMaxSize()) {
            DhcTitle(
                titleState = DhcTitleState(
                    title = stringResource(R.string.intro_birth_day_title),
                    titleStyle = DhcTypoTokens.TitleH1,
                ),
                textAlign = TextAlign.Start,
                subTitleState = DhcTitleState(
                    title = stringResource(R.string.intro_birth_day_sub_title),
                    titleStyle = DhcTypoTokens.Body3,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp, start = 20.dp, end = 20.dp),
            )
            Spacer(modifier = Modifier.height(84.dp))
            CalendarTypeSelector(
                selectedValue = state.calendarType,
                onSelectValue = { value ->
                    eventHandler(IntroBirthDayContract.Event.SelectCalendarType(value))
                },
            )
            Spacer(modifier = Modifier.height(13.dp))
            DhcDaySpinner(
                onValueChanged = { year, month, day ->
                    eventHandler(IntroBirthDayContract.Event.SelectBirthDay(year, month, day))
                },
                modifier = Modifier.fillMaxWidth(),
                initialYear = state.year,
                initialMonth = state.month,
                initialDay = state.day,
            )
        }
        DhcButton(
            text = stringResource(R.string.start_with_finance_luck),
            buttonSize = DhcButtonSize.XLARGE,
            buttonStyle = DhcButtonStyle.Secondary(isEnabled = true),
            onClick = { eventHandler(IntroBirthDayContract.Event.ClickNextButton) },
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
        )
    }
}

@Composable
fun CalendarTypeSelector(
    selectedValue: CalendarType,
    onSelectValue: (CalendarType) -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = LocalDhcColors.current
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Text(
            text = stringResource(R.string.birth_day),
            style = TextStyle(
                fontFamily = typoFontFamily,
                fontWeight = FontWeight.W500,
                fontSize = 14.sp,
                lineHeight = 20.sp,
            ),
            color = colors.text.textBodyPrimary,
        )
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            CalendarType.entries.forEach { calendarType ->
                DhcButton(
                    text = stringResource(calendarType.textResourceId),
                    buttonSize = DhcButtonSize.SMALL,
                    buttonStyle = if (calendarType == selectedValue) {
                        DhcButtonStyle.Primary(isEnabled = true)
                    } else {
                        DhcButtonStyle.Secondary(isEnabled = true)
                    },
                    onClick = { onSelectValue(calendarType) },
                    modifier = Modifier.width(80.dp),
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun IntroBirthDayScreenPreview() {
    DhcTheme {
        IntroBirthDayScreen(
            state = IntroBirthDayContract.State(),
            eventHandler = {},
        )
    }
}
