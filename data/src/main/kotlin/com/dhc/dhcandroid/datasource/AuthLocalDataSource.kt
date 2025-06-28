package com.dhc.dhcandroid.datasource

import kotlinx.coroutines.flow.Flow

interface AuthLocalDataSource {
    suspend fun setUUID()
    suspend fun getUUID(): Flow<String?>

    suspend fun setFcmToken(token: String)
    suspend fun getFcmToken(): Flow<String?>
}
