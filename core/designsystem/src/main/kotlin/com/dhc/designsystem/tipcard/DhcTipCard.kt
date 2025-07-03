package com.dhc.designsystem.tipcard

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.dhc.common.hexToColor
import com.dhc.designsystem.DhcTheme
import com.dhc.designsystem.DhcTypoTokens
import com.dhc.designsystem.LocalDhcColors
import com.dhc.designsystem.SurfaceColor
import com.dhc.designsystem.getSvgImageLoader


@Composable
fun DhcTipCard(
    tipCardItem: TipCardModel,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = SurfaceColor.neutral700, shape = RoundedCornerShape(12.dp))
            .padding(horizontal = 16.dp, vertical = 20.dp)
    ) {
        TipCardTitle(
            title = tipCardItem.title,
            icon = tipCardItem.icon
        )
        Spacer(modifier = Modifier.height(8.dp))
        TipCardContent(
            color = tipCardItem.color,
            content = tipCardItem.cont
        )
    }
}

@Composable
fun TipCardTitle(
    title: String,
    icon: String,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        AsyncImage(
            model = icon,
            contentDescription = "tipCardIcon",
            imageLoader = context.getSvgImageLoader(),
            modifier = Modifier.size(20.dp),
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = title,
            style = DhcTypoTokens.Body5,
            color = SurfaceColor.neutral400,
        )
    }
}


@Composable
fun TipCardContent(
    color: String?,
    content: String,
    modifier: Modifier = Modifier,
) {
    val dhcColor = LocalDhcColors.current
    Row(
        modifier = modifier.wrapContentSize(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        color?.let {
            Canvas(modifier = Modifier.size(8.dp)) {
                drawCircle(
                    color = hexToColor(color),
                    radius = size.minDimension / 2,
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
        }
        Text(
            text = content,
            style = DhcTypoTokens.TitleH3,
            color = if(color != null) hexToColor(color) else dhcColor.text.textBodyPrimary,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
@Preview
private fun PreviewTipCard(
    @PreviewParameter(DhcTipCardPreviewProvider::class)
    parameter: TipCardModel,
) {
    DhcTheme {
        DhcTipCard(
            tipCardItem = parameter
        )
    }
}
private class DhcTipCardPreviewProvider :
    PreviewParameterProvider<TipCardModel> {
    override val values = sequenceOf(
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
}
