package com.dhc.dhcandroid.datasource

interface UserLocalDataSource {
    suspend fun setUUID(uuid: String)
    suspend fun getUUID(): String?

    suspend fun setFcmToken(token: String)
    suspend fun getFcmToken(): String?
}