package com.dhc.designsystem.messagecard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dhc.designsystem.DhcTheme
import com.dhc.designsystem.DhcTypoTokens
import com.dhc.designsystem.LocalDhcColors
import com.dhc.designsystem.SurfaceColor

@Composable
fun DhcMessageCard(
    title: String,
    content: String
) {
    val dhcColor = LocalDhcColors.current
    Column(
        modifier = Modifier
            .background(
                color = SurfaceColor.neutral700,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(horizontal = 16.dp, vertical = 20.dp)
    ) {
        Text(
            text = title,
            style = DhcTypoTokens.Body5,
            color = SurfaceColor.neutral400,

        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = content,
            style = DhcTypoTokens.Body3,
            color = dhcColor.text.textBodyPrimary,
        )
    }
}

@Preview
@Composable
private fun PreviewDhcMessageCard() {
    DhcTheme {
        DhcMessageCard(
            title = "금전운",
            content = "오늘은 지갑을 더 단단히 쥐고 계셔야겠어요.\n" +
                    "괜히 시선 가는 거 많고, 충동구매가 살짝 \n" +
                    "걱정되는 날이에요.\n" +
                    "꼭 필요한 소비인지 한 번만 더 생각해보면, \n" +
                    "내일의 나에게 분명 고마워할 거예요.\n" +
                    "\n" +
                    "행운의 색인 연두색이 들어간 소품을 곁에 두면 \n" +
                    "조금 더 차분한 하루가 될지도 몰라요."
        )
    }
}