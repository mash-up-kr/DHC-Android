package com.dhc.intro.intro

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dhc.intro.R

@Composable
fun IntroScreen(
    modifier: Modifier = Modifier,
    navigateToNextScreen: () -> Unit,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.padding(top = 44.dp))
        Text(
            text = stringResource(R.string.intro_main_title),
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF313131), // Todo: Use design system color
            textAlign = TextAlign.Center,
            lineHeight = 32.sp,
        )
        Spacer(modifier = Modifier.padding(top = 16.dp))
        Text(
            text = stringResource(R.string.intro_sub_title),
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF999999), // Todo: Use design system color
            textAlign = TextAlign.Center,
            lineHeight = 24.sp,
        )
        Spacer(modifier = Modifier.padding(top = 48.dp))
        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center,
        ) {
            // Todo : 인트로 이미지 추가
            Box(
                modifier = Modifier
                    .size(width = 260.dp, height = 350.dp)
                    .background(Color.Cyan)
            )
        }
        Spacer(modifier = Modifier.padding(top = 54.dp))
        NextButton(
            text = stringResource(R.string.button_start),
            onClick = navigateToNextScreen,
        )
    }
}

@Composable
private fun NextButton(
    text: String,
    onClick: () -> Unit,
) {
    Text(
        text = text,
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold,
        color = Color(0xFFFFFFFF),
        textAlign = TextAlign.Center,
        modifier = Modifier
            .padding(all = 20.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFF313131)) // Todo: Use design system color
            .padding(vertical = 15.dp)
            .clickable { onClick() }
    )
}

@Preview(showBackground = true)
@Composable
private fun TutorialScreenPreview() {
    IntroScreen {}
}
