package com.dhc.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.dhc.designsystem.badge.DhcBadge
import com.dhc.designsystem.badge.model.BadgeType
import com.dhc.designsystem.dialog.DhcDialog

@Composable
fun MissionSuccessDialog() {
    DhcDialog(
        dialogContent = {
            Column(
                modifier = Modifier.fillMaxWidth(),
            ) {
                DhcBadge(
                    text = "미션 성공",
                    BadgeType.Date
                )

            }
        }
    )
}