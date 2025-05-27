package com.dhc.dhcandroid

import android.annotation.SuppressLint
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.dhc.dhcandroid.ui.theme.DHCAndroidTheme
import com.dhc.sample.home.HomeRoute
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        getUUID()
        setContent {
            DHCAndroidTheme {
                HomeRoute()
            }
        }
    }


    @SuppressLint("HardwareIds")
    private fun getUUID() {
        val userId = Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
    }
}
