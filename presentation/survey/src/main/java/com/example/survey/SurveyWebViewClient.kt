package com.example.survey

import android.webkit.WebView
import android.webkit.WebViewClient

class SurveyWebViewClient(
    val canGoBackChanged: (Boolean) -> Unit,
) : WebViewClient() {
    override fun doUpdateVisitedHistory(view: WebView?, url: String?, isReload: Boolean) {
        super.doUpdateVisitedHistory(view, url, isReload)
        view?.canGoBack()?.let { canGoBackChanged(it) }
    }
}