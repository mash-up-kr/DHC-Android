package com.dhc.dhcandroid.repository

import kotlinx.coroutines.flow.Flow

interface AuthDataStoreRepository {
    suspend fun getUUID(): Flow<String?>
    suspend fun setUUID()

    suspend fun getFcmToken(): Flow<String?>
    suspend fun setFcmToken(token: String)
}
