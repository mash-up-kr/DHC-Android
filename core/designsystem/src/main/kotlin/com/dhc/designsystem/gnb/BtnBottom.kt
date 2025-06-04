package com.dhc.designsystem.gnb

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
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
import androidx.compose.ui.unit.dp
import com.dhc.designsystem.DHCAndroidTheme
import com.dhc.designsystem.R

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

data class GnbItem(
    @DrawableRes val iconResource: Int,
    @StringRes val iconText: Int,
    val routeName: String,
)

@Preview
@Composable
private fun DhcBtnBottomSelectedPreview() {
    DHCAndroidTheme {
        DhcBtnBottom(
            gnbItem = GnbItem(
                iconResource = R.drawable.home_04,
                iconText = R.string.btn_bottom_home,
                routeName = "MAIN_HOME",
            ),
            isSelected = true,
            onClick = {},
            modifier = Modifier,
        )
    }
}

@Preview
@Composable
private fun DhcBtnBottomNotSelectedPreview() {
    DHCAndroidTheme {
        DhcBtnBottom(
            gnbItem = GnbItem(
                iconResource = R.drawable.home_04,
                iconText = R.string.btn_bottom_home,
                routeName = "MAIN_HOME",
            ),
            isSelected = false,
            onClick = {},
            modifier = Modifier,
        )
    }
}
