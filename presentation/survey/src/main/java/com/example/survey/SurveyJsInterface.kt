package com.example.survey

import android.app.Activity
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
}
