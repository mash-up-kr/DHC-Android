package com.dhc.dhcandroid.repository

import kotlinx.coroutines.flow.Flow

interface AuthDataStoreRepository {
    suspend fun setUserId(userId: String)
    suspend fun getUserId(): Flow<String?>

    suspend fun setUserToken(token: String)
    suspend fun getUserToken(): Flow<String?>

    suspend fun getUUID(): String?

    suspend fun getFcmToken(): Flow<String?>
    suspend fun setFcmToken(token: String)
}
