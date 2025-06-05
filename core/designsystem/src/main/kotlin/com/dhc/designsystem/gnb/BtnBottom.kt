package com.dhc.designsystem.gnb

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.dhc.designsystem.DHCAndroidTheme
import com.dhc.designsystem.R
import com.dhc.designsystem.gnb.model.GnbItem

@Composable
fun DhcBtnBottom(
    gnbItem: GnbItem,
    isSelected: Boolean, // Todo : 디자인시스템 적용 후 함께 적용예정
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.clickable { onClick(gnbItem.routeName) },
        verticalArrangement = Arrangement.spacedBy(2.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(gnbItem.iconResource),
            contentDescription = null,
        )

        // TODO@xemic: 텍스트에 디자인시스템 스타일 적용하기
        Text(
            text = stringResource(gnbItem.iconText),
        )
    }
}


class GnbItemPreviewProvider : PreviewParameterProvider<GnbItemPreviewProvider.DhcBottomParameter> {
    private val homeGnbItem = GnbItem(
        iconResource = R.drawable.home_04,
        iconText = R.string.btn_bottom_home,
        routeName = "MAIN_HOME",
    )
    override val values = sequenceOf(
        DhcBottomParameter(
            gnbItem = homeGnbItem,
            isSelected = true,
        ),
        DhcBottomParameter(
            gnbItem = homeGnbItem,
            isSelected = false,
        ),
    )

    data class DhcBottomParameter(
        val gnbItem: GnbItem,
        val isSelected: Boolean,
    )
}

@Preview
@Composable
fun BtnBottomPreview(
    @PreviewParameter(GnbItemPreviewProvider::class)
    parameter: GnbItemPreviewProvider.DhcBottomParameter,
) {
    DHCAndroidTheme {
        DhcBtnBottom(
            gnbItem = parameter.gnbItem,
            isSelected = parameter.isSelected,
            onClick = {},
            modifier = Modifier,
        )
    }
}
