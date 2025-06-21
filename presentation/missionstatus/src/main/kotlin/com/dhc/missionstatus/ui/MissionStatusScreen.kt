package com.dhc.missionstatus.ui

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dhc.designsystem.DhcTheme
import com.dhc.missionstatus.MissionStatusContract.State

@Composable
fun MissionStatusScreen(
    state: State,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {

    }
}

@Preview
@Composable
private fun MissionStatusScreenPreview() {
    DhcTheme {
        MissionStatusScreen(state = State())
    }
}
