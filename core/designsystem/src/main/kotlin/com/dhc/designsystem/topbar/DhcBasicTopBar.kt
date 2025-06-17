package com.dhc.designsystem.topbar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.dhc.designsystem.DhcTheme
import com.dhc.designsystem.DhcTypoTokens
import com.dhc.designsystem.LocalDhcColors
import com.dhc.designsystem.R
import com.dhc.designsystem.SurfaceColor
import com.dhc.designsystem.topbar.model.TopBarPageState

@Composable
fun DhcBasicTopBar(
    title: String,
    isShowBackButton: Boolean,
    modifier: Modifier = Modifier,
    topBarPageState: TopBarPageState? = null,
    onClickBackButton: () -> Unit,
) {
    val colors = LocalDhcColors.current
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 12.dp, end = 12.dp, top = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (isShowBackButton) {
            Icon(
                painter = painterResource(R.drawable.ico_arrow_left),
                contentDescription = "Back",
                tint = SurfaceColor.neutral50,
                modifier = Modifier
                    .clickable { onClickBackButton() }
                    .padding(10.dp)
                    .size(24.dp),
            )
        }

        Text(
            modifier = Modifier
                .weight(1f)
                .padding(9.dp),
            text = title,
            style = DhcTypoTokens.Body2,
            color = colors.text.textMain,
            textAlign = TextAlign.Center,
        )

        if (topBarPageState != null) {
            Row(
                modifier = Modifier.padding(horizontal = 9.5.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = "${topBarPageState.currentPage}",
                    style = DhcTypoTokens.TitleH5,
                    color = colors.text.textMain,
                )
                Spacer(modifier = Modifier.width(2.dp))
                Text(
                    text = "/${topBarPageState.totalPage}",
                    style = DhcTypoTokens.Body3,
                    color = colors.text.textMain.copy(alpha = 0.2f),
                )
            }
        } else if (isShowBackButton) {
            Spacer(modifier = Modifier.size(44.dp)) // back 버튼만 있을 때 Title 의 가운데 정렬을 위한 공간 확보
        }
    }
}

private class DhcBasicTopBarPreviewProvider : PreviewParameterProvider<DhcBasicTopBarPreviewProvider.Parameter> {
    override val values = sequenceOf(
        Parameter(
            title = "화면 제목",
            isShowBackButton = false,
            topBarPageState = null,
        ),
        Parameter(
            title = "화면 제목",
            isShowBackButton = true,
            topBarPageState = null,
        ),
        Parameter(
            title = "화면 제목",
            isShowBackButton = true,
            topBarPageState = TopBarPageState(
                currentPage = 1,
                totalPage = 4,
            ),
        ),
        Parameter(
            title = "",
            isShowBackButton = true,
            topBarPageState = TopBarPageState(
                currentPage = 1,
                totalPage = 3,
            ),
        ),
        Parameter(
            title = "",
            isShowBackButton = false,
            topBarPageState = TopBarPageState(
                currentPage = 1,
                totalPage = 4,
            ),
        ),
    )

    data class Parameter(
        val title: String,
        val isShowBackButton: Boolean,
        val topBarPageState: TopBarPageState? = null,
    )
}

@Preview
@Composable
private fun DhcBasicTopBarPreview(
    @PreviewParameter(DhcBasicTopBarPreviewProvider::class)
    parameter: DhcBasicTopBarPreviewProvider.Parameter,
) {
    DhcTheme {
        DhcBasicTopBar(
            topBarPageState = parameter.topBarPageState,
            isShowBackButton = parameter.isShowBackButton,
            title = parameter.title,
            onClickBackButton = {},
        )
    }
}
