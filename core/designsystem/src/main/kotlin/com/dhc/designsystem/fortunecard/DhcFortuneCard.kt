package com.dhc.designsystem.fortunecard

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.dhc.common.ImageResource
import com.dhc.designsystem.DhcTheme
import com.dhc.designsystem.DhcTypoTokens
import com.dhc.designsystem.GradientColor
import com.dhc.designsystem.LocalDhcColors
import com.dhc.designsystem.SurfaceColor

@Composable
fun DhcFortuneCard(
    title: String,
    description: String,
    imageResource: ImageResource?,
    modifier: Modifier = Modifier,
) {
    when (imageResource) {
        is ImageResource.Drawable -> {
            DhcFortuneCard(
                title = title,
                description = description,
                cardDrawableResId = imageResource.resId,
                modifier = modifier,
            )
        }
        is ImageResource.Url -> {
            DhcFortuneCard(
                title = title,
                description = description,
                imageUrl = imageResource.url,
                modifier = modifier,
            )
        }
        else -> Unit
    }
}
@Composable
fun DhcFortuneCard(
    title: String,
    description: String,
    imageUrl: String?,
    modifier: Modifier = Modifier,
) {
    DhcFortuneCardInternal(
        title = title,
        description = description,
        modifier = modifier.size(143.dp, 197.dp),
        backgroundContent = {
            if (imageUrl != null) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = "운세카드 이미지",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize(),
                )
            } else {
                Spacer(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(SurfaceColor.neutral700),
                )
            }
        },
    )
}

@Composable
fun DhcFortuneCard(
    title: String,
    description: String,
    @DrawableRes cardDrawableResId: Int?,
    modifier: Modifier = Modifier,
) {
    DhcFortuneCardInternal(
        title = title,
        description = description,
        modifier = modifier.size(144.dp, 200.dp),
        backgroundContent = {
            if (cardDrawableResId != null) {
                Image(
                    painter = painterResource(cardDrawableResId),
                    contentDescription = "운세카드 이미지",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize(),
                )
            } else {
                Spacer(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(SurfaceColor.neutral700),
                )
            }
        },
    )
}

@Composable
fun DhcFortuneCardInternal(
    title: String,
    description: String,
    modifier: Modifier = Modifier,
    backgroundContent: @Composable BoxScope.() -> Unit,
) {
    val colors = LocalDhcColors.current
    Box(
        modifier = modifier
            .shadow(
                elevation = 2.dp,
                shape = RoundedCornerShape(12.dp),
            )
            .border(
                border = BorderStroke(
                    width = 1.dp,
                    brush = GradientColor.cardBorderGradient01,
                ),
                shape = RoundedCornerShape(12.dp),
            )
            .shadow(
                elevation = 2.dp,
                shape = RoundedCornerShape(12.dp),
            ),
    ) {
        backgroundContent()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 20.dp, horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = title,
                style = DhcTypoTokens.TitleH8.copy(brush = GradientColor.textGradient02),
                textAlign = TextAlign.Center,
            )
            Text(
                modifier = Modifier.padding(bottom = 8.dp),
                text = description,
                style = DhcTypoTokens.TitleH6,
                textAlign = TextAlign.Center,
                color = colors.text.textBodyPrimary,
            )
        }
    }
}

private class DhcFortuneCardPreviewProvider : PreviewParameterProvider<DhcFortuneCardPreviewProvider.Parameter> {
    override val values = sequenceOf(
        Parameter(
            title = "최고의 날",
            description = "네잎클로버",
        ),
        Parameter(
            title = "카드 뒷면",
            description = "임시",
        ),
    )

    data class Parameter(
        val title: String,
        val description: String,
    )
}

@Preview
@Composable
private fun DhcFortuneCardPreview(
    @PreviewParameter(DhcFortuneCardPreviewProvider::class)
    parameter: DhcFortuneCardPreviewProvider.Parameter,
) {
    DhcTheme {
        DhcFortuneCard(
            title = parameter.title,
            description = parameter.description,
            modifier = Modifier.size(143.dp, 197.dp),
            cardDrawableResId = null,
        )
    }
}
