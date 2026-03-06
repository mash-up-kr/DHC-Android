package com.dhc.dhcandroid.di

import android.content.Context
import com.dhc.data.BuildConfig
import com.dhc.dhcandroid.datasource.AuthLocalDataSource
import com.dhc.dhcandroid.datasource.AuthLocalDataSourceImpl
import com.dhc.dhcandroid.datasource.DhcMockDataSourceImpl
import com.dhc.dhcandroid.datasource.UserMemoryDataSource
import com.dhc.dhcandroid.datasource.UserMemoryDataSourceImpl
import com.dhc.dhcandroid.datasource.DhcRemoteDataSource
import com.dhc.dhcandroid.datasource.DhcRemoteDataSourceImpl
import com.dhc.dhcandroid.datasource.EasterEggDataSource
import com.dhc.dhcandroid.datasource.EasterEggDataSourceImpl
import com.dhc.dhcandroid.datasource.FortuneDataSource
import com.dhc.dhcandroid.datasource.FortuneDataSourceImpl
import com.dhc.dhcandroid.datasource.UserLocalDataSource
import com.dhc.dhcandroid.datasource.UserLocalDataSourceImpl
import com.dhc.dhcandroid.service.DhcService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun bindsAuthLocalDataSource(
        authLocalDataSourceImpl: AuthLocalDataSourceImpl
    ): AuthLocalDataSource

    @Binds
    @Singleton
    abstract fun bindsUserMemoryDataSource(
        userMemoryDataSourceImpl: UserMemoryDataSourceImpl
    ): UserMemoryDataSource

    @Binds
    @Singleton
    abstract fun bindsUserLocalDataSource(
        userLocalDataSourceImpl: UserLocalDataSourceImpl
    ): UserLocalDataSource

    @Binds
    @Singleton
    abstract fun bindsFortuneDataSource(
        fortuneDataSourceImpl: FortuneDataSourceImpl
    ): FortuneDataSource

    @Binds
    @Singleton
    abstract fun bindsEasterEggDataSource(
        easterEggDataSourceImpl: EasterEggDataSourceImpl
    ): EasterEggDataSource
}

@Module
@InstallIn(SingletonComponent::class)
internal object DataSourceProviderModule {

    @Provides
    @Singleton
    fun provideDhcRemoteDataSource(
        @ApplicationContext context: Context,
        dhcService: DhcService,
    ): DhcRemoteDataSource = when (BuildConfig.FLAVOR) {
        "mock" -> DhcMockDataSourceImpl(context)
        else -> DhcRemoteDataSourceImpl(dhcService)
    }
}
