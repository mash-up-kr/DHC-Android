package com.dhc.designsystem.category

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.dhc.designsystem.DhcTheme
import com.dhc.designsystem.DhcTypoTokens
import com.dhc.designsystem.LocalDhcColors
import com.dhc.designsystem.R
import com.dhc.designsystem.SurfaceColor
import com.dhc.designsystem.check.DhcCheck
import com.dhc.designsystem.check.model.DhcCheckStyle

@Composable
fun DhcCategoryItem(
    name: String,
    isChecked: Boolean,
    imageUrl: String,
    modifier: Modifier = Modifier,
) {
    val colors = LocalDhcColors.current

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
            .clip(shape = RoundedCornerShape(12.dp))
            .background(
                color = if (isChecked) {
                    colors.background.backgroundBadgePrimary
                } else {
                    SurfaceColor.neutral700
                },
                shape = RoundedCornerShape(12.dp)
            )
            .padding(16.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp),
        ) {
            Text(
                text = name,
                style = DhcTypoTokens.TitleH5,
                color = if (isChecked) {
                    colors.text.textHighLightsSecondary
                } else {
                    colors.text.textBodyPrimary
                },
                modifier = Modifier.weight(1f),
            )
            if (isChecked) {
                DhcCheck(
                    isChecked = true,
                    isEnabled = true,
                    dhcCheckStyle = DhcCheckStyle(
                        containerSize = 24.dp,
                        iconSize = 14.dp,
                    ),
                )
            }
        }
        AsyncImage(
            model = imageUrl,
            contentDescription = name,
            modifier = Modifier.size(36.dp),
        )
    }
}

private class DhcCategoryPreviewProvider : PreviewParameterProvider<DhcCategoryPreviewProvider.Parameter> {
    override val values = sequenceOf(
        Parameter(
            name = "식음료",
            isChecked = false,
            iconResource = R.drawable.ico_check,
        ),
        Parameter(
            name = "식음료",
            isChecked = true,
            iconResource = R.drawable.ico_check,
        ),
    )

    data class Parameter(
        val name: String,
        val isChecked: Boolean,
        @DrawableRes val iconResource: Int,
    )
}

@Preview
@Composable
private fun DhcCategoryPreview(
    @PreviewParameter(DhcCategoryPreviewProvider::class)
    parameter: DhcCategoryPreviewProvider.Parameter,
) {
    DhcTheme {
        DhcCategoryItem(
            name = parameter.name,
            isChecked = parameter.isChecked,
            imageUrl = "",
        )
    }
}
