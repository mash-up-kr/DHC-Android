package com.dhc.designsystem.spinner

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dhc.designsystem.DhcTheme
import com.dhc.designsystem.LocalDhcColors
import com.dhc.designsystem.R
import com.dhc.designsystem.spinner.common.DhcItemSpinnerBackground
import com.dhc.designsystem.spinner.common.DhcItemSpinnerContent
import com.dhc.designsystem.spinner.common.DhcSpinner
import com.dhc.designsystem.spinner.model.TimeType

@Composable
fun DhcTimeSpinner(
    onValueChanged: (timeType: TimeType, time: Int, minute: Int) -> Unit,
    modifier: Modifier = Modifier,
    visibleItemsCount: Int = 5,
    itemHeightDp: Dp = 44.dp,
    initialTimeType: TimeType = TimeType.AM,
    initialTime: Int = 3,
    initialMinute: Int = 10,
) {
    val colors = LocalDhcColors.current
    var currentTimeType by remember { mutableStateOf(initialTimeType) }
    var currentTime by remember { mutableIntStateOf(initialTime) }
    var currentMinute by remember { mutableIntStateOf(initialMinute) }

    val timeTypeList = remember { TimeType.entries.toList() }
    val timeList = remember { (1..12).toList() }
    val minuteList = remember { (0 until 60 step 5).toList() }

    LaunchedEffect(currentTimeType, currentTime, currentMinute) {
        onValueChanged(currentTimeType, currentTime, currentMinute)
    }

    Row(
        modifier = modifier
            .background(color = colors.background.backgroundMain)
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(23.dp),
    ) {
        DhcSpinner(
            itemList = timeTypeList,
            visibleItemsCount = visibleItemsCount,
            itemHeightDp = itemHeightDp,
            onValueChanged = { currentTimeType = it },
            modifier = Modifier.weight(2f),
            initialIndex = timeTypeList.indexOf(initialTimeType),
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
                    text = item.text,
                    isFocused = isFocused,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(itemHeightDp),
                )
            }
        )
        DhcSpinner(
            itemList = timeList,
            visibleItemsCount = visibleItemsCount,
            itemHeightDp = itemHeightDp,
            onValueChanged = { currentTime = it },
            modifier = Modifier.weight(1f),
            initialIndex = timeList.indexOf(initialTime).coerceAtLeast(0),
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
                    text = stringResource(R.string.d_hour, item),
                    isFocused = isFocused,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(itemHeightDp),
                )
            }
        )
        DhcSpinner(
            itemList = minuteList,
            visibleItemsCount = visibleItemsCount,
            itemHeightDp = itemHeightDp,
            onValueChanged = { currentMinute = it },
            modifier = Modifier.weight(1f),
            initialIndex = minuteList.indexOf(initialMinute).coerceAtLeast(0),
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
                    text = stringResource(R.string.d_minute, item),
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
        DhcTimeSpinner(
            onValueChanged = { _, _, _ -> },
            visibleItemsCount = 5,
            itemHeightDp = 44.dp,
            initialTimeType = TimeType.AM,
            initialTime = 10,
            initialMinute = 15,
        )
    }
}
