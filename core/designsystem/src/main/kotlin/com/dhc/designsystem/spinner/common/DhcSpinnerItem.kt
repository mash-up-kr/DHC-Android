package com.dhc.designsystem.spinner.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dhc.designsystem.DhcTheme

@Composable
fun <T : Any> DhcSpinnerItem(
    itemList: Collection<T>,
    visibleItemsCount: Int,
    itemHeightDp: Dp,
    initialItem: T,
    onValueChanged: (T) -> Unit,
    itemTextCreator: @Composable (T) -> String,
    modifier: Modifier = Modifier,
) {
    DhcSpinner(
        itemList = itemList,
        visibleItemsCount = visibleItemsCount,
        itemHeightDp = itemHeightDp,
        onValueChanged = onValueChanged,
        modifier = modifier,
        initialIndex = itemList.indexOf(initialItem).coerceAtLeast(0),
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
                text = itemTextCreator(item),
                isFocused = isFocused,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(itemHeightDp),
            )
        }
    )
}

@Preview
@Composable
private fun DhcSpinnerItemPreview() {
    DhcTheme {
        DhcSpinnerItem(
            itemList = (1900..2025).toList(),
            visibleItemsCount = 5,
            itemHeightDp = 48.dp,
            initialItem = 2000,
            onValueChanged = {},
            itemTextCreator = { it.toString() },
            modifier = Modifier.fillMaxWidth(),
        )
    }
}
