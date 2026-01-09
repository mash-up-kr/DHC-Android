package com.dhc.reward.yearfortune

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dhc.designsystem.DhcTheme
import com.dhc.designsystem.DhcTypoTokens
import com.dhc.designsystem.GradientColor
import com.dhc.designsystem.fortunecard.DhcFortuneCard
import com.dhc.designsystem.messagecard.DhcMessageCard
import com.dhc.designsystem.score.DhcScoreText
import com.dhc.designsystem.score.toGradientScoreColor
import com.dhc.designsystem.tipcard.DhcTipCard
import com.dhc.designsystem.tipcard.DhcTipCardGrid
import com.dhc.designsystem.title.DhcTitle
import com.dhc.designsystem.title.DhcTitleState
import com.dhc.presentation.ui.monetaryDetail.FortuneCard
import com.dhc.presentation.ui.monetaryDetail.ScoreInfo

@Composable
fun YearFortuneScreen(
    yearFortuneInfo: YearFortuneInfo,
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
                badgeText = yearFortuneInfo.scoreInfo.date,
                score = yearFortuneInfo.scoreInfo.score,
                description = yearFortuneInfo.scoreInfo.description,
                scoreTextColor = yearFortuneInfo.scoreInfo.score.toGradientScoreColor()
            )
            Spacer(modifier = Modifier.height(64.dp))
            DhcFortuneCard(
                title = yearFortuneInfo.fortuneCard.title,
                description = yearFortuneInfo.fortuneCard.message,
                imageResource = yearFortuneInfo.fortuneCard.image,
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
            
            // 전반적인 운세
            OverallFortuneSection(message = yearFortuneInfo.overallFortune)
            Spacer(modifier = Modifier.height(24.dp))
            
            // 한 눈에 보는 운세
            QuickViewFortuneSection(items = yearFortuneInfo.quickViewFortune)
            Spacer(modifier = Modifier.height(24.dp))
            
            // 내년 내 사주 오행의 균형
            FiveElementGaugeContent(
                modifier = Modifier.fillMaxWidth(),
                fiveElementData = yearFortuneInfo.fiveElementData
            )
            Spacer(modifier = Modifier.height(24.dp))
            
            // 올해의 기운 변화
            EnergyChangeSection(message = yearFortuneInfo.energyChange)
            Spacer(modifier = Modifier.height(24.dp))
            
            // 이번년도 꿀팁
            YearTipsSection(tips = yearFortuneInfo.yearTips)
            Spacer(modifier = Modifier.height(97.dp))
        }
    }
}

@Composable
private fun OverallFortuneSection(
    message: String,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DhcTitle(
            titleState = DhcTitleState(
                title = "전반적인 운세",
                titleStyle = DhcTypoTokens.TitleH4_1,
            ),
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(12.dp))
        DhcMessageCard(
            modifier = Modifier.fillMaxWidth(),
            title = "타이틀",
            content = message
        )
    }
}

@Composable
private fun QuickViewFortuneSection(
    items: List<QuickViewFortuneItem>,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DhcTitle(
            titleState = DhcTitleState(
                title = "한 눈에 보는 운세",
                titleStyle = DhcTypoTokens.TitleH5_1,
            ),
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(12.dp))
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items.forEach { item ->
                DhcTipCard(
                    tipCardItem = com.dhc.designsystem.tipcard.TipCardModel(
                        title = item.title,
                        cont = item.content,
                        icon = item.icon,
                        color = null
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    contentTextStyle = DhcTypoTokens.Body3,
                    contentTextAlign = TextAlign.Start
                )
            }
        }
    }
}

@Composable
private fun EnergyChangeSection(
    message: String,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DhcTitle(
            titleState = DhcTitleState(
                title = "올해의 기운 변화",
                titleStyle = DhcTypoTokens.TitleH5_1,
            ),
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(12.dp))
        DhcMessageCard(
            modifier = Modifier.fillMaxWidth(),
            title = "타이틀",
            content = message
        )
    }
}

@Composable
private fun YearTipsSection(
    tips: List<com.dhc.designsystem.tipcard.TipCardModel>,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DhcTitle(
            titleState = DhcTitleState(
                title = "이번년도 꿀팁",
                titleStyle = DhcTypoTokens.TitleH5_1,
            ),
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(12.dp))
        DhcTipCardGrid(tipCards = tips)
    }
}

@Preview
@Composable
private fun PreviewYearFortuneScreen() {
    DhcTheme {
        YearFortuneScreen(
            yearFortuneInfo = YearFortuneInfo(
                scoreInfo = ScoreInfo(
                    date = "2026년 운세 총평",
                    score = 99,
                    description = "올 한해는 전반적으로 마음이 들뜨는 날이에요,\n한템포 쉬어가요."
                ),
                fortuneCard = FortuneCard(
                    title = "최고의 날",
                    message = "네잎클로버",
                ),
                overallFortune = "이번 달은 마음이 한층 단단해지는 시기예요.\n불필요한 걱정에 에너지를 쓰기보단, 지금 눈앞의 상황에 집중하면 일이 자연스럽게 풀려갑니다.\n충동적인 선택이 아니라 조금 더 생각하고 결정하는 것만으로도 내일의 나에게 분명 고마운 한 달이 될 거예요.",
                quickViewFortune = listOf(
                    QuickViewFortuneItem(
                        title = "금전운",
                        content = "현재의 상황을 최우선적으로 고려해보는 것이 좋아요. 과거의 상황까지 고려할 필요는 없어요."
                    ),
                    QuickViewFortuneItem(
                        title = "연애운",
                        content = "상대방의 말보다 분위기를 읽는 게 관계에 좋은 영향이 있어요."
                    ),
                    QuickViewFortuneItem(
                        title = "학업운",
                        content = "현재의 상황을 최우선적으로 고려해보는 것이 좋아요. 과거의 상황까지 고려할 필요는 없어요."
                    )
                ),
                fiveElementData = listOf(
                    FiveElementData.fromPercentage(FiveElementType.WOOD, 20),
                    FiveElementData.fromPercentage(FiveElementType.FIRE, 40),
                    FiveElementData.fromPercentage(FiveElementType.EARTH, 30),
                    FiveElementData.fromPercentage(FiveElementType.METAL, 20),
                    FiveElementData.fromPercentage(FiveElementType.WATER, 10)
                ),
                energyChange = "화의 기운은\n'결단력・집중・주체성'을 밝히는 에너지예요.\n불안이나 충동으로 흐르면 지치기 쉽지만,\n올바르게 쓰이면 원하는 방향으로 크게 나아가는 달이 됩니다.\n\n그래서 이번 달엔…\n• 지금의 상황을 기준으로 결정해보세요.\n• 감정보다는 리듬을 안정시키면 잘 흘러가요.\n• 내일의 나에게 분명 고마운 선택을 하게 될 거예요.",
                yearTips = listOf(
                    com.dhc.designsystem.tipcard.TipCardModel(
                        title = "추천메뉴",
                        icon = null,
                        color = null,
                        cont = "카레"
                    ),
                    com.dhc.designsystem.tipcard.TipCardModel(
                        title = "행운의 색상",
                        icon = null,
                        color = "#23B169",
                        cont = "연두색"
                    ),
                    com.dhc.designsystem.tipcard.TipCardModel(
                        title = "피해야 할 음식",
                        icon = null,
                        color = null,
                        cont = "치킨, 닭"
                    ),
                    com.dhc.designsystem.tipcard.TipCardModel(
                        title = "피해야 할 색상",
                        icon = null,
                        color = "#F4F4F5",
                        cont = "흰색"
                    )
                )
            )
        )
    }
}