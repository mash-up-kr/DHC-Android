package com.dhc.designsystem.check

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.dhc.designsystem.DhcTheme
import com.dhc.designsystem.LocalDhcColors
import com.dhc.designsystem.R
import com.dhc.designsystem.SurfaceColor
import com.dhc.designsystem.check.model.DhcCheckStyle

@Composable
fun DhcCheck(
    isChecked: Boolean,
    isEnabled: Boolean,
    dhcCheckStyle: DhcCheckStyle,
) {
    val colors = LocalDhcColors.current
    val backgroundColor = when {
        isEnabled.not() -> SurfaceColor.neutral500
        isChecked -> colors.text.textHighLightsPrimary
        else -> SurfaceColor.neutral400
    }
    val iconTint = if (isEnabled) colors.text.textMain else SurfaceColor.neutral400

    Box(
        modifier = Modifier
            .size(dhcCheckStyle.containerSize)
            .background(
                color = backgroundColor,
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            painter = painterResource(R.drawable.ico_check),
            contentDescription = "check",
            tint = iconTint,
            modifier = Modifier.size(dhcCheckStyle.iconSize),
        )
    }
}

private class CheckPreviewProvider : PreviewParameterProvider<CheckPreviewProvider.Parameter> {
    override val values = sequenceOf(
        Parameter(
            isChecked = true,
            isEnabled = true,
            dhcCheckStyle = DhcCheckStyle.Default,
        ),
        Parameter(
            isChecked = false,
            isEnabled = true,
            dhcCheckStyle = DhcCheckStyle.Default,
        ),
        Parameter(
            isChecked = true,
            isEnabled = false,
            dhcCheckStyle = DhcCheckStyle.Default,
        ),
    )

    data class Parameter(
        val isChecked: Boolean,
        val isEnabled: Boolean,
        val dhcCheckStyle: DhcCheckStyle,
    )
}

@Preview
@Composable
private fun DhcCheckPreview(
    @PreviewParameter(CheckPreviewProvider::class)
    parameter: CheckPreviewProvider.Parameter,
) {
    DhcTheme {
        DhcCheck(
            isChecked = parameter.isChecked,
            isEnabled = parameter.isEnabled,
            dhcCheckStyle = parameter.dhcCheckStyle,
        )
    }
}
