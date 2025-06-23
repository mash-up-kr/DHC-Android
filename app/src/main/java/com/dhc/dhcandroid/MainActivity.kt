package com.dhc.dhcandroid

import android.Manifest.permission.POST_NOTIFICATIONS
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.WindowCompat
import com.dhc.designsystem.DhcTheme
import com.dhc.dhcandroid.navigation.DhcApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        WindowCompat.getInsetsController(window, window.decorView)
            .isAppearanceLightStatusBars = false
        requestNotificationPermission()
        setContent {
            DhcTheme {
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
