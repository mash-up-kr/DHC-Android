package com.dhc.dhcandroid

import android.Manifest.permission.POST_NOTIFICATIONS
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import com.dhc.designsystem.DHCAndroidTheme
import com.dhc.dhcandroid.navigation.DhcApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        requestNotificationPermission()
        setContent {
            DHCAndroidTheme {
                DhcApp()
            }
        }
    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission(),
    ) { isGranted: Boolean ->
        if (!isGranted) {
            // TODO - 기획 상 추가되는 토스트 메세지 등 필요
        }
    }

    private fun requestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (shouldShowRequestPermissionRationale(POST_NOTIFICATIONS)) {
                // 이전에 거부한 경우 권한 필요성 설명 및 권한 요청
            } else {
                requestPermissionLauncher.launch(POST_NOTIFICATIONS)
            }
        }
    }
}
