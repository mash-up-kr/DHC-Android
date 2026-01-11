package com.dhc.reward.yearfortune

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun YearFortuneRoute(
    modifier: Modifier = Modifier,
) {
    // TODO: 실제 데이터를 가져오는 로직이 필요하면 ViewModel을 통해 가져와야 함
    // 현재는 Preview에서 사용하는 샘플 데이터를 사용
    YearFortuneScreen(
        yearFortuneInfo = YearFortuneInfo(),
        modifier = modifier
    )
}
