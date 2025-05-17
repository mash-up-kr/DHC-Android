package com.dhc.dhcandroid.di

import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideDhcRetrofit(
        okHttpClient: OkHttpClient,
    ): Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideDhcOkHttpClient(
        flipperOkhttpInterceptor: FlipperOkhttpInterceptor,
    ): OkHttpClient = OkHttpClient.Builder().apply {
        addInterceptor(flipperOkhttpInterceptor)
    }.build()

    @Provides
    @Singleton
    fun provideFlipperOkHttpPlugin(
        networkFlipperPlugin: NetworkFlipperPlugin
    ): FlipperOkhttpInterceptor = FlipperOkhttpInterceptor(networkFlipperPlugin, true)

    @Provides
    @Singleton
    fun providesNetworkFlipperPlugin(): NetworkFlipperPlugin =
        NetworkFlipperPlugin()
}