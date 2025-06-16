package com.dhc.designsystem.badge

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.dhc.designsystem.DhcTheme
import com.dhc.designsystem.badge.model.BadgeType

@Composable
fun DhcBadge(
    text: String,
    type: BadgeType,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        color = type.textColor,
        style = type.textStyle,
        modifier = modifier
            .background(
                color = type.backgroundColor,
                shape = RoundedCornerShape(type.cornerRadius),
            )
            .padding(horizontal = 12.dp, vertical = 4.dp),
    )
}

private class BadgePreviewProvider : PreviewParameterProvider<BadgePreviewProvider.Parameter> {
    override val values = sequenceOf(
        Parameter(
            text = "Easy",
            type = BadgeType.Level(isEnabled = true),
        ),
        Parameter(
            text = "Easy",
            type = BadgeType.Level(isEnabled = false),
        ),
        Parameter(
            text = "커피값 절약",
            type = BadgeType.SpendType,
        ),
        Parameter(
            text = "2025년 5월 20일",
            type = BadgeType.Date,
        ),
    )

    data class Parameter(
        val text: String,
        val type: BadgeType,
    )
}

@Preview
@Composable
private fun DhcBadgePreview(
    @PreviewParameter(BadgePreviewProvider::class)
    parameter: BadgePreviewProvider.Parameter,
) {
    DhcTheme {
        DhcBadge(
            text = parameter.text,
            type = parameter.type,
        )
    }
}
