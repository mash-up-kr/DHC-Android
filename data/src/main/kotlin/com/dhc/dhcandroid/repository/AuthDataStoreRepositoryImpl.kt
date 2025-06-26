package com.dhc.dhcandroid.repository

import com.dhc.dhcandroid.datasource.AuthLocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthDataStoreRepositoryImpl @Inject constructor(
    private val authLocalDataSource: AuthLocalDataSource
) : AuthDataStoreRepository {
    override suspend fun getUUID(): Flow<String?> {
        return authLocalDataSource.getUUID()
    }

    override suspend fun setUUID() {
        authLocalDataSource.setUUID()
    }

    override suspend fun getFcmToken(): Flow<String?> {
        return authLocalDataSource.getFcmToken()
    }

    override suspend fun setFcmToken(token: String) {
        authLocalDataSource.setFcmToken(token = token)
    }
}
