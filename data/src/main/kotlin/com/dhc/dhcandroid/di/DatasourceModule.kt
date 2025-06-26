package com.dhc.dhcandroid.di

import com.dhc.dhcandroid.datasource.DhcRemoteDataSource
import com.dhc.dhcandroid.datasource.DhcRemoteDataSourceImpl
import com.dhc.dhcandroid.datasource.UserLocalDataSource
import com.dhc.dhcandroid.datasource.UserLocalDataSourceImpl
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
    abstract fun bindsUserLocalDataSource(
        userLocalDataSourceImpl: UserLocalDataSourceImpl
    ): UserLocalDataSource

    @Binds
    @Singleton
    abstract fun bindsDhcRemoteDataSource(
        dhcRemoteDataSourceImpl: DhcRemoteDataSourceImpl
    ): DhcRemoteDataSource
}
