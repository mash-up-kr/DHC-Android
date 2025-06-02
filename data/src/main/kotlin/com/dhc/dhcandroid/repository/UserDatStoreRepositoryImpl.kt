package com.dhc.dhcandroid.repository

import com.dhc.dhcandroid.datasource.UserLocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserDatStoreRepositoryImpl @Inject constructor(
    private val userLocalDataSource: UserLocalDataSource
) : UserDataStoreRepository {
    override suspend fun getUUID(): Flow<String?> {
        return userLocalDataSource.getUUID()
    }

    override suspend fun setUUID(uuid: String) {
        userLocalDataSource.setUUID(uuid = uuid)
    }

    override suspend fun getFcmToken(): Flow<String?> {
        return userLocalDataSource.getFcmToken()
    }

    override suspend fun setFcmToken(token: String) {
        userLocalDataSource.setFcmToken(token = token)
    }
}