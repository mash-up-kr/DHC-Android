package com.dhc.dhcandroid.datasource

import kotlinx.coroutines.flow.Flow

interface AuthLocalDataSource {
    suspend fun setUserId(userId: String)
    suspend fun getUserId(): Flow<String?>

    suspend fun setUserToken(token: String)
    suspend fun getUserToken(): Flow<String?>

    suspend fun getUUID(): String?

    suspend fun setFcmToken(token: String)
    suspend fun getFcmToken(): Flow<String?>
}
