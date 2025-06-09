package com.dhc.designsystem.spinner.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dhc.designsystem.DhcTheme
import kotlinx.coroutines.launch

@Composable
fun <T : Any> DhcSpinner(
    itemList: Collection<T>,
    visibleItemsCount: Int,
    itemHeightDp: Dp,
    onValueChanged: (T) -> Unit,
    modifier: Modifier = Modifier,
    initialIndex: Int = 0,
    itemBackground: @Composable ColumnScope.(isFocused: Boolean) -> Unit = {},
    itemContent: @Composable BoxScope.(item: T, isFocused: Boolean) -> Unit,
) {
    val dummyValueCount = remember(visibleItemsCount) { (visibleItemsCount - 1) / 2 }
    val inputItemList = remember(dummyValueCount, itemList) {
        buildList {
            repeat(dummyValueCount) { add(null) }
            addAll(itemList)
            repeat(dummyValueCount) { add(null) }
        }
    }
    val listState = rememberLazyListState(initialFirstVisibleItemIndex = initialIndex)
    val coroutineScope = rememberCoroutineScope()
    var currentOriginalItemIndex by remember { mutableIntStateOf(initialIndex) }

    LaunchedEffect(listState) {
        snapshotFlow { listState.firstVisibleItemIndex }
            .collect { index ->
                currentOriginalItemIndex = index
                inputItemList[index + dummyValueCount]?.let { item ->
                    onValueChanged(item)
                }
            }
    }

    LaunchedEffect(listState) {
        snapshotFlow { listState.isScrollInProgress }
            .collect { isScrollInProgress ->
                if (isScrollInProgress.not()) {
                    coroutineScope.launch {
                        listState.animateScrollToItem(currentOriginalItemIndex)
                    }
                }
            }
    }

    Box(modifier = modifier.height(itemHeightDp * visibleItemsCount)) {
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxSize()
        ) {
            (0 until visibleItemsCount).forEach { index ->
                itemBackground(index == (visibleItemsCount / 2))
            }
        }

        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxSize(),
        ) {
            items(
                count = inputItemList.size,
                key = { it },
            ) { index ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(itemHeightDp),
                    contentAlignment = Alignment.Center
                ) {
                    inputItemList[index]?.let {
                        itemContent(
                            it,
                            index == currentOriginalItemIndex + dummyValueCount
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DhcSpinnerPreview() {
    DhcTheme {
        DhcSpinner(
            itemList = (1..12).toList(),
            visibleItemsCount = 3,
            itemHeightDp = 51.dp,
            onValueChanged = {},
            modifier = Modifier.fillMaxWidth(),
            initialIndex = 3,
            itemBackground = {},
            itemContent = { item, _ -> Text(text = "${item}월") }
        )
    }
}
