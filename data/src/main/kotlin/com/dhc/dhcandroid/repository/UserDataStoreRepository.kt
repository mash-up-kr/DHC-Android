package com.dhc.dhcandroid.repository

import kotlinx.coroutines.flow.Flow

interface UserDataStoreRepository {
    suspend fun getUUID(): Flow<String?>
    suspend fun setUUID(uuid: String)

    suspend fun getFcmToken(): Flow<String?>
    suspend fun setFcmToken(token: String)
}