package com.dhc.dhcandroid.di

import com.dhc.dhcandroid.datasource.AuthLocalDataSource
import com.dhc.dhcandroid.datasource.AuthLocalDataSourceImpl
import com.dhc.dhcandroid.datasource.UserMemoryDataSource
import com.dhc.dhcandroid.datasource.UserMemoryDataSourceImpl
import com.dhc.dhcandroid.datasource.DhcRemoteDataSource
import com.dhc.dhcandroid.datasource.DhcRemoteDataSourceImpl
import com.dhc.dhcandroid.datasource.DhcRemoteDataSourceMock
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

    @Impl
    @Binds
    @Singleton
    abstract fun bindsDhcRemoteDataSourceImpl(
        dhcRemoteDataSourceImpl: DhcRemoteDataSourceImpl
    ): DhcRemoteDataSource

    @Mock
    @Binds
    @Singleton
    abstract fun bindsDhcRemoteDataSourceMock(
        dhcRemoteDataSourceImpl: DhcRemoteDataSourceMock
    ): DhcRemoteDataSource
}
