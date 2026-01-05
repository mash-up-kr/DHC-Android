package com.dhc.designsystem.modal

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dhc.designsystem.DhcTheme
import com.dhc.designsystem.DhcTypoTokens
import com.dhc.designsystem.LocalDhcColors
import com.dhc.designsystem.R
import com.dhc.designsystem.SurfaceColor
import com.dhc.designsystem.button.DhcButton
import com.dhc.designsystem.button.model.DhcButtonSize
import com.dhc.designsystem.button.model.DhcButtonStyle

@Composable
fun DhcModal(
    onClickClose: () -> Unit,
    onClickSubmit: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = LocalDhcColors.current
    Column(
        modifier = modifier
            .background(
                color = SurfaceColor.neutral700,
                shape = RoundedCornerShape(12.dp),
            )
    ) {
        Box(
            contentAlignment = Alignment.CenterEnd,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Image(
                painter = painterResource(R.drawable.ico_x),
                contentDescription = "취소",
                colorFilter = ColorFilter.tint(color = SurfaceColor.neutral300),
                modifier = Modifier
                    .padding(7.dp)
                    .clickable(onClick = onClickClose)
                    .padding(top = 16.dp, end = 12.dp),
            )
        }
        Spacer(modifier = Modifier.height(12.dp))

        // Todo : 그래픽 확정시 그에 맞게 DhcModal 변경 예정
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(128.dp)
                .background(Color(0xFF2E3341))
        ) {
            Text(
                text = "그래픽 (변경예정)",
                color = Color.White,
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "궁합 테스트에 참여하고\n스페셜 미션 받아보세요",
            style = DhcTypoTokens.TitleH4_1,
            color = colors.text.textBodyPrimary,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "지금까지 389명이 참여했어요!",
            style = DhcTypoTokens.Body5,
            color = SurfaceColor.neutral300,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(12.dp))
        DhcButton(
            text = "테스트 참여하기",
            buttonSize = DhcButtonSize.LARGE,
            buttonStyle = DhcButtonStyle.Primary(isEnabled = true),
            onClick = onClickSubmit,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
        )
        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Preview
@Composable
private fun DhcPopupPreview() {
    DhcTheme {
        DhcModal(
            onClickClose = {},
            onClickSubmit = {},
        )
    }
}