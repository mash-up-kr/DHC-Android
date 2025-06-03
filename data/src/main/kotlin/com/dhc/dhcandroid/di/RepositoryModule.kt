package com.dhc.dhcandroid.di

import com.dhc.dhcandroid.repository.UserDatStoreRepositoryImpl
import com.dhc.dhcandroid.repository.UserDataStoreRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindUserDataStoreRepository(
        userDatStoreRepositoryImpl: UserDatStoreRepositoryImpl
    ): UserDataStoreRepository

}
