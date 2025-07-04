package com.dhc.dhcandroid.repository

import android.util.Base64
import com.dhc.dhcandroid.datasource.AuthLocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AuthDataStoreRepositoryImpl @Inject constructor(
    private val authLocalDataSource: AuthLocalDataSource
) : AuthDataStoreRepository {
    override suspend fun setUserId(userId: String) {
        authLocalDataSource.setUserId(userId)
    }

    override suspend fun getUserId(): Flow<String?> =
        authLocalDataSource.getUserId()

    override suspend fun getEncodedUserId(): Flow<String?> =
        authLocalDataSource.getUserId().map {
            Base64.encodeToString(it?.toByteArray(), Base64.DEFAULT)
        }

    override suspend fun clearUserId() {
        authLocalDataSource.setUserId("")
    }

    override suspend fun setUserToken(token: String) {
        authLocalDataSource.setUserToken(token)
    }

    override suspend fun getUserToken(): Flow<String?> =
        authLocalDataSource.getUserToken()

    override suspend fun getUUID(): String? =
        authLocalDataSource.getUUID()

    override suspend fun getFcmToken(): Flow<String?> {
        return authLocalDataSource.getFcmToken()
    }

    override suspend fun setFcmToken(token: String) {
        authLocalDataSource.setFcmToken(token = token)
    }
}
