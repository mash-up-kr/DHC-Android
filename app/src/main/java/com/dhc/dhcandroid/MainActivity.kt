package com.dhc.dhcandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.dhc.dhcandroid.navigation.DhcApp
import com.dhc.dhcandroid.ui.theme.DHCAndroidTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            DHCAndroidTheme {
                DhcApp()
            }
        }
    }
}
