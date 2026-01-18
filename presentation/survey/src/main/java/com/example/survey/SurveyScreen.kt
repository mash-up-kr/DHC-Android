package com.example.survey

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.webkit.CookieManager
import android.webkit.WebView
import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.viewinterop.AndroidView
import com.dhc.designsystem.LocalDhcColors

private const val DhcWebUrl = "https://dhc-web.vercel.app/"

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun SurveyScreen(
    state: SurveyContract.State,
    navigateToHome: () -> Unit,
    navigateToPrevScreen: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val activity = LocalActivity.current
    val colors = LocalDhcColors.current
    AndroidView(
        factory = { context ->
            state.shareToken?.let {
                val cookieManager = CookieManager.getInstance()
                cookieManager.setAcceptCookie(true)
                cookieManager.setCookie(DhcWebUrl, "shareToken=${state.shareToken}")
                cookieManager.flush()
            }

            WebView(context).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT,
                )
                settings.javaScriptEnabled = true
                settings.domStorageEnabled = true // 클라이언트 사이드 라우팅 동작을 위함
                settings.userAgentString = buildString {
                    append(settings.userAgentString)
                    append(" DHCApp")
                }
                setBackgroundColor(colors.background.backgroundMain.toArgb())
                addJavascriptInterface(
                    SurveyJsInterface(
                        activity,
                        navigateToHome,
                        navigateToPrevScreen,
                    ),
                    "DHCJavascriptInterface",
                )
                loadUrl(DhcWebUrl)
            }
        },
        modifier = modifier,
    )
}
