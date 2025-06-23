package com.dhc.designsystem.info

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dhc.designsystem.DhcTheme
import com.dhc.designsystem.DhcTypoTokens
import com.dhc.designsystem.LocalDhcColors
import com.dhc.designsystem.R
import com.dhc.designsystem.SurfaceColor

@Composable
fun DhcMissionStatusCard(
    title: String,
    subTitle: String,
    modifier: Modifier = Modifier,
    backgroundColor: Color = SurfaceColor.neutral700
) {
    Row(
        modifier = modifier
            .background(color = backgroundColor, shape = RoundedCornerShape(8.dp))
            .padding(vertical = 12.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        DhcInfoCardIcon(
            iconRes = R.drawable.ico_fly_money,
            modifier = Modifier.size(54.dp)
        )
        DhcInfoCardText(
            modifier = Modifier.padding(start = 12.dp),
            title = title,
            subTitle = subTitle,
        )
    }
}

@Composable
private fun DhcInfoCardIcon(
    @DrawableRes iconRes: Int,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.background(SurfaceColor.neutral800, shape = CircleShape)) {
        Image(
            modifier = Modifier.align(Alignment.Center),
            painter = painterResource(iconRes),
            contentDescription = "icon something",
        )
    }
}

@Composable
private fun DhcInfoCardText(
    title: String,
    subTitle: String,
    modifier: Modifier = Modifier,
) {
    val colors = LocalDhcColors.current

    Column(modifier = modifier) {
        Text(
            text = title,
            style = DhcTypoTokens.Body3,
            color = colors.text.textBodyPrimary,
        )
        Text(
            text = subTitle,
            style = DhcTypoTokens.TitleH3,
            color = colors.text.textMain,
        )
    }
}

@Preview
@Composable
private fun DhcMoneyInfoCardPreview() {
    DhcTheme {
        DhcMissionStatusCard(
            modifier = Modifier.fillMaxWidth(),
            title = "텍스트",
            subTitle = "텍스트",
        )
    }
}
