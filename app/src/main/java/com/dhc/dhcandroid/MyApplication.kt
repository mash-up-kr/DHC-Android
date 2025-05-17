package com.dhc.dhcandroid

import android.app.Application
import android.content.Context
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.android.utils.FlipperUtils
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.facebook.soloader.SoLoader
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MyApplication : Application() {
    @Inject
    lateinit var networkFlipperPlugin: NetworkFlipperPlugin

    override fun onCreate() {
        super.onCreate()
        initFlipper(this, networkFlipperPlugin)
    }
}

private fun initFlipper(context: Context, networkFlipperPlugin: NetworkFlipperPlugin) {
    SoLoader.init(context, false)
    if (context.getString(R.string.build_type) == "debug" && FlipperUtils.shouldEnableFlipper(context)) {
        val client = AndroidFlipperClient.getInstance(context).apply {
            // 기본 Flipper 플러그인
            addPlugin(
                InspectorFlipperPlugin(
                    context,
                    DescriptorMapping.withDefaults()
                )
            )
            addPlugin(InspectorFlipperPlugin(context, DescriptorMapping.withDefaults())) // Layout 플러그인
            addPlugin(networkFlipperPlugin) // network 플러그인
        }
        client.start()
    }
}