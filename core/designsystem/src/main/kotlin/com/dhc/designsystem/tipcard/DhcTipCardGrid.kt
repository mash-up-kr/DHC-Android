package com.dhc.designsystem.tipcard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dhc.common.ImageResource
import com.dhc.designsystem.DhcTheme

@Composable
fun DhcTipCardGrid(
    tipCards: List<TipCardModel>,
    modifier: Modifier = Modifier,
    cellCount: Int = 2,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        repeat(cellCount) { rowIndex ->
            Row(
                modifier = Modifier.fillMaxWidth().height(IntrinsicSize.Max),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                for (columnIndex in 0 until tipCards.size / cellCount) {
                    val itemIndex = columnIndex * cellCount + rowIndex
                    DhcTipCard(tipCards[itemIndex], Modifier.weight(1f).fillMaxHeight())
                }
            }
        }
    }
}



@Composable
@Preview
fun PreviewDhcTipCardLazyGrid() {
    DhcTheme {
        DhcTipCardGrid(
            tipCards = listOf(
                TipCardModel(
                    title = "오늘의 추천 메뉴 추천 메뉴",
                    icon = ImageResource.Url("https://foodish-api.com/images/pizza/pizza80.jpg"),
                    color = null,
                    cont = "치킨이닭"
                ),
                TipCardModel(
                    title = "행운의 색상",
                    icon = ImageResource.Url("https://foodish-api.com/images/pizza/pizza80.jpg"),
                    color = "#23B169",
                    cont = "연두색"
                ),
                TipCardModel(
                    title = "오늘의 추천 메뉴",
                    icon = ImageResource.Url("https://foodish-api.com/images/pizza/pizza80.jpg"),
                    color = null,
                    cont = "치킨이닭"
                ),
                TipCardModel(
                    title = "행운의 색상",
                    icon = ImageResource.Url("https://foodish-api.com/images/pizza/pizza80.jpg"),
                    color = "#23B169",
                    cont = "연두색"
                ),
            )
        )
    }
}
