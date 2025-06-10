package com.dhc.designsystem.spinner

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dhc.common.CalendarUtil
import com.dhc.designsystem.DhcTheme
import com.dhc.designsystem.LocalDhcColors
import com.dhc.designsystem.R
import com.dhc.designsystem.spinner.common.DhcItemSpinnerBackground
import com.dhc.designsystem.spinner.common.DhcItemSpinnerContent
import com.dhc.designsystem.spinner.common.DhcSpinner

@Composable
fun DhcDaySpinner(
    onValueChanged: (year: Int, month: Int, day: Int) -> Unit,
    modifier: Modifier = Modifier,
    visibleItemsCount: Int = 5,
    itemHeightDp: Dp = 44.dp,
    initialYear: Int = 2000,
    initialMonth: Int = 3,
    initialDay: Int = 3,
) {
    val colors = LocalDhcColors.current
    var currentYear by remember { mutableIntStateOf(initialYear) }
    var currentMonth by remember { mutableIntStateOf(initialMonth) }
    var currentDay by remember { mutableIntStateOf(initialDay) }

    val yearList = remember { (1900..CalendarUtil.getCurrentYear()).toList() }
    val monthList = remember { (1..12).toList() }
    val dayList by remember(currentYear, currentMonth) {
        derivedStateOf {
            (1..CalendarUtil.getActualMaximumDayOfMonth(currentYear, currentMonth)).toList()
        }
    }

    LaunchedEffect(currentYear, currentMonth, currentDay, dayList) {
        if (dayList.contains(currentDay)) {
            onValueChanged(currentYear, currentMonth, currentDay)
        }
    }

    Row(
        modifier = modifier
            .background(color = colors.background.backgroundMain)
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(23.dp),
    ) {
        DhcSpinner(
            itemList = yearList,
            visibleItemsCount = visibleItemsCount,
            itemHeightDp = itemHeightDp,
            onValueChanged = { currentYear = it },
            modifier = Modifier.weight(2f),
            initialIndex = yearList.indexOf(initialYear).coerceAtLeast(0),
            itemBackground = { isFocused ->
                DhcItemSpinnerBackground(
                    isFocused = isFocused,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(itemHeightDp),
                )
            },
            itemContent = { item, isFocused ->
                DhcItemSpinnerContent(
                    text = stringResource(R.string.d_year, item),
                    isFocused = isFocused,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(itemHeightDp),
                )
            }
        )
        DhcSpinner(
            itemList = monthList,
            visibleItemsCount = visibleItemsCount,
            itemHeightDp = itemHeightDp,
            onValueChanged = { currentMonth = it },
            modifier = Modifier.weight(1f),
            initialIndex = monthList.indexOf(initialMonth).coerceAtLeast(0),
            itemBackground = { isFocused ->
                DhcItemSpinnerBackground(
                    isFocused = isFocused,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(itemHeightDp),
                )
            },
            itemContent = { item, isFocused ->
                DhcItemSpinnerContent(
                    text = stringResource(R.string.d_month, item),
                    isFocused = isFocused,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(itemHeightDp),
                )
            }
        )
        DhcSpinner(
            itemList = dayList,
            visibleItemsCount = visibleItemsCount,
            itemHeightDp = itemHeightDp,
            onValueChanged = { currentDay = it },
            modifier = Modifier.weight(1f),
            initialIndex = dayList.indexOf(initialDay).coerceAtLeast(0),
            itemBackground = { isFocused ->
                DhcItemSpinnerBackground(
                    isFocused = isFocused,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(itemHeightDp),
                )
            },
            itemContent = { item, isFocused ->
                DhcItemSpinnerContent(
                    text = stringResource(R.string.d_day, item),
                    isFocused = isFocused,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(itemHeightDp),
                )
            }
        )
    }
}

@Preview
@Composable
private fun DhcDaySpinnerPreview() {
    DhcTheme {
        DhcDaySpinner(
            onValueChanged = { _, _, _ -> },
            visibleItemsCount = 5,
            itemHeightDp = 44.dp,
            initialYear = 2023,
            initialMonth = 10,
            initialDay = 15,
        )
    }
}
