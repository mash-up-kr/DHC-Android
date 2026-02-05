package com.example.survey

import android.app.Activity
import android.content.Intent
import android.webkit.JavascriptInterface
import android.widget.Toast

class SurveyJsInterface(
    private val activity: Activity?,
    private val navigateToHome: () -> Unit,
    private val navigateToPrevScreen: () -> Unit,
) {

    @JavascriptInterface
    fun close() {
        activity?.runOnUiThread { navigateToPrevScreen() }
    }

    @JavascriptInterface
    fun goToMain() {
        activity?.runOnUiThread { navigateToHome() }
    }

    @JavascriptInterface
    fun showToast(message: String) {
        activity?.let {
            Toast.makeText(it, message, Toast.LENGTH_SHORT).show()
        }
    }

    @JavascriptInterface
    fun share(title: String, url: String) {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, title)
            putExtra(Intent.EXTRA_TEXT, url)
        }
        activity?.startActivity(Intent.createChooser(intent, title))
    }
}
