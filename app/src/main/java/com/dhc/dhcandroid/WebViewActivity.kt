package com.dhc.dhcandroid

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.webkit.JavascriptInterface
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.dhc.designsystem.DhcTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WebViewActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DhcTheme {
                Scaffold { paddingValues ->
                    WebViewScreen(modifier = Modifier.fillMaxSize().padding(paddingValues))
                }
            }
        }
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(
                Intent(context, WebViewActivity::class.java)
            )
        }
    }
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebViewScreen(modifier: Modifier = Modifier) {
    val activity = LocalActivity.current
    AndroidView(
        factory = { context ->
            Log.e("zemic", "hello world")
            WebView(context).apply {
                settings.javaScriptEnabled = true // 뒤로가기 등의 액션 받기 위함
                settings.domStorageEnabled = true // 클라이언트 사이드 라우팅 동작을 위함
                settings.userAgentString = "${settings.userAgentString} DHCApp"
                setBackgroundColor(Color.BLACK) // 웹 페이지 배경색과 맞춤
                loadUrl("https://dhc-web.vercel.app/")
                addJavascriptInterface(WebAppInterface(activity), "DHCJavascriptInterface")
            }
        },
        modifier = modifier,
    )
}

class WebAppInterface(private val activity: Activity?) {

    @JavascriptInterface
    fun close() {
        activity?.finish()
    }

    @JavascriptInterface
    fun goToMain() {
        activity?.finish()
    }

    @JavascriptInterface
    fun showToast(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }
}