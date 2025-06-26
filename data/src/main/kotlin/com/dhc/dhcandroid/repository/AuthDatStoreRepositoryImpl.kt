package com.dhc.dhcandroid.repository

import com.dhc.dhcandroid.datasource.AuthLocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthDatStoreRepositoryImpl @Inject constructor(
    private val AuthLocalDataSource: AuthLocalDataSource
) : AuthDataStoreRepository {
    override suspend fun getUUID(): Flow<String?> {
        return AuthLocalDataSource.getUUID()
    }

    override suspend fun setUUID() {
        AuthLocalDataSource.setUUID()
    }

    override suspend fun getFcmToken(): Flow<String?> {
        return AuthLocalDataSource.getFcmToken()
    }

    override suspend fun setFcmToken(token: String) {
        AuthLocalDataSource.setFcmToken(token = token)
    }
}
