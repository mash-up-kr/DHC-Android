package com.dhc.presentation.ui.monetaryDetail

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dhc.common.ImageResource
import com.dhc.designsystem.DhcTheme
import com.dhc.designsystem.DhcTypoTokens
import com.dhc.designsystem.GradientColor
import com.dhc.designsystem.fortunecard.DhcFortuneCard
import com.dhc.designsystem.messagecard.DhcMessageCard
import com.dhc.designsystem.score.DhcScoreText
import com.dhc.designsystem.score.toGradientScoreColor
import com.dhc.designsystem.tipcard.DhcTipCardGrid
import com.dhc.designsystem.tipcard.TipCardModel
import com.dhc.designsystem.title.DhcTitle
import com.dhc.designsystem.title.DhcTitleState
import com.dhc.presentation.R

@Composable
fun MonetaryLuckDetailScreen(
    monetaryLuckInfo: MonetaryLuckInfo,
    modifier: Modifier = Modifier,
    scrollState: ScrollState = rememberScrollState()
) {
    Box(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            DhcScoreText(
                badgeText = monetaryLuckInfo.scoreInfo.date,
                score = monetaryLuckInfo.scoreInfo.score,
                description = monetaryLuckInfo.scoreInfo.description,
                scoreTextColor = monetaryLuckInfo.scoreInfo.score.toGradientScoreColor()
            )
            Spacer(modifier = Modifier.height(64.dp))
            DhcFortuneCard(
                title = monetaryLuckInfo.fortuneCard.title,
                description = monetaryLuckInfo.fortuneCard.message,
                imageResource = monetaryLuckInfo.fortuneCard.image,
                modifier = Modifier.size(width = 144.dp, height = 200.dp),
            )
            Spacer(modifier = Modifier.height(12.dp))
            Canvas(
                modifier = Modifier
                    .size(width = 32.dp, height = 32.dp)
                    .graphicsLayer { scaleX = 4f },
            ) {
                drawOval(
                    brush = GradientColor.cardBottomGradient01,
                    size = size,
                    alpha = 0.4f,
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            MonetaryLuckDetailCard(message = monetaryLuckInfo.monetaryDetail)
            Spacer(modifier = Modifier.height(24.dp))
            TodayTip(tips = monetaryLuckInfo.todayTips)
            Spacer(modifier = Modifier.height(97.dp))
        }
    }
}


@Composable
fun MonetaryLuckDetailCard(
    message: String,
) {
    DhcTitle(
        titleState = DhcTitleState(
            title = stringResource(R.string.monetary_luck_detail),
            titleStyle = DhcTypoTokens.TitleH5_1,
        ),
        textAlign = TextAlign.Start,
    )
    Spacer(modifier = Modifier.height(12.dp))
    DhcMessageCard(
        modifier = Modifier.fillMaxWidth(),
        title = "금전운",
        content = message
    )
}

@Composable
fun TodayTip(
    tips: List<TipCardModel>,
) {
    DhcTitle(
        titleState = DhcTitleState(
            title = stringResource(R.string.today_tip),
            titleStyle = DhcTypoTokens.TitleH5_1,
        ),
        textAlign = TextAlign.Start,
    )
    Spacer(modifier = Modifier.height(12.dp))
    DhcTipCardGrid(tipCards = tips)
}

@Preview
@Composable
private fun PreviewMonetaryLuckDetail() {
    DhcTheme {
        MonetaryLuckDetailScreen(
            monetaryLuckInfo = MonetaryLuckInfo(
                scoreInfo = ScoreInfo(
                    date = "2025년 5월 20일",
                    score = 35,
                    description = "마음이 들뜨는 날이에요,\n한템포 쉬어가요."
                ),
                fortuneCard = FortuneCard(
                    message = "한템포 쉬어가기,"
                ),
                monetaryDetail = "오늘은 지갑을 더 단단히 쥐고 계셔야겠어요.\n" +
                        "괜히 시선 가는 거 많고, 충동구매가 살짝 \n" +
                        "걱정되는 날이에요.\n" +
                        "꼭 필요한 소비인지 한 번만 더 생각해보면, \n" +
                        "내일의 나에게 분명 고마워할 거예요.\n" +
                        "\n" +
                        "행운의 색인 연두색이 들어간 소품을 곁에 두면 \n" +
                        "조금 더 차분한 하루가 될지도 몰라요.",
                todayTips = listOf(
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
        )
    }
}
