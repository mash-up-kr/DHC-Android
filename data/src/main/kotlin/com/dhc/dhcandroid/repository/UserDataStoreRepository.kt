package com.dhc.dhcandroid.repository

interface UserDataStoreRepository {
    suspend fun getUUID(): String?
    suspend fun setUUID(uuid: String)

    suspend fun getFcmToken(): String?
    suspend fun setFcmToken(token: String)
}