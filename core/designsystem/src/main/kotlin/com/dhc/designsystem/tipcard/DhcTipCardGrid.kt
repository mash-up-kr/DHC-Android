package com.dhc.designsystem.tipcard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dhc.designsystem.DhcTheme

@Composable
fun DhcTipCardLazyGrid(
    tipCards: List<TipCardModel>,
    cellCount: Int = 2,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(cellCount),
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(tipCards) { tipCard ->
            DhcTipCard(
                tipCardItem = tipCard,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}



@Composable
@Preview
fun PreviewDhcTipCardLazyGrid() {
    DhcTheme {
        DhcTipCardLazyGrid(
            tipCards = listOf(
                TipCardModel(
                    title = "오늘의 추천 메뉴",
                    icon = "https://foodish-api.com/images/pizza/pizza80.jpg",
                    color = null,
                    cont = "치킨이닭"
                ),
                TipCardModel(
                    title = "행운의 색상",
                    icon = "https://foodish-api.com/images/pizza/pizza80.jpg",
                    color = "#23B169",
                    cont = "연두색"
                ),
                TipCardModel(
                    title = "오늘의 추천 메뉴",
                    icon = "https://foodish-api.com/images/pizza/pizza80.jpg",
                    color = null,
                    cont = "치킨이닭"
                ),
                TipCardModel(
                    title = "행운의 색상",
                    icon = "https://foodish-api.com/images/pizza/pizza80.jpg",
                    color = "#23B169",
                    cont = "연두색"
                ),
            )
        )
    }
}
