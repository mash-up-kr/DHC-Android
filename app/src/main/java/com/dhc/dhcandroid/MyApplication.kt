package com.dhc.dhcandroid

import android.app.Application
import com.facebook.flipper.BuildConfig
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
        initFlipper(networkFlipperPlugin)
    }

    private fun initFlipper(networkFlipperPlugin: NetworkFlipperPlugin) {
        SoLoader.init(this, false)
        if (BuildConfig.DEBUG && FlipperUtils.shouldEnableFlipper(this)) {
            val client = AndroidFlipperClient.getInstance(this@MyApplication).apply {
                addPlugin(InspectorFlipperPlugin(this@MyApplication, DescriptorMapping.withDefaults())) // Layout 플러그인
                addPlugin(networkFlipperPlugin) // network 플러그인
            }
            client.start()
        }
    }
}
