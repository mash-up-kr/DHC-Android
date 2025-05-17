package com.dhc.dhcandroid

import android.app.Application
import android.content.Context
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.android.utils.FlipperUtils
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.soloader.SoLoader
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initFlipper(this)
    }
}

private fun initFlipper(context: Context) {
    SoLoader.init(context, false)
    if (context.getString(R.string.build_type) == "debug" && FlipperUtils.shouldEnableFlipper(context)) {
        val client = AndroidFlipperClient.getInstance(context).apply {
            addPlugin(
                InspectorFlipperPlugin(
                    context,
                    DescriptorMapping.withDefaults()
                )
            ) // 기본 Flipper 플러그인
            addPlugin(InspectorFlipperPlugin(context, DescriptorMapping.withDefaults())) // Flipper Layout 플러그인
        }
        client.start()
    }
}