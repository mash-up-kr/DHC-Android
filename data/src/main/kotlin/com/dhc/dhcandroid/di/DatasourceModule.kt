package com.dhc.dhcandroid.di

import com.dhc.dhcandroid.datasource.AuthLocalDataSource
import com.dhc.dhcandroid.datasource.AuthLocalDataSourceImpl
import com.dhc.dhcandroid.datasource.UserMemoryDataSource
import com.dhc.dhcandroid.datasource.UserMemoryDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
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
}
