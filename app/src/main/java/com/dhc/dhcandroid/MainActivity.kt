package com.dhc.dhcandroid

import android.Manifest.permission.POST_NOTIFICATIONS
import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.dhc.dhcandroid.ui.theme.DHCAndroidTheme
import com.dhc.sample.home.HomeRoute
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.AndroidEntryPoint
import kotlin.getValue

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        getUUID()
        requestNotificationPermission()
        mainViewModel.getFcmToken()

        setContent {
            DHCAndroidTheme {
                HomeRoute()
            }
        }
    }

    @SuppressLint("HardwareIds")
    private fun getUUID() {
        val userId = Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
        Log.d(TAG, userId)
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
