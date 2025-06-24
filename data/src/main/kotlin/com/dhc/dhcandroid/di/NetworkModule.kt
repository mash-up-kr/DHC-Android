package com.dhc.dhcandroid.di

import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
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
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
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
