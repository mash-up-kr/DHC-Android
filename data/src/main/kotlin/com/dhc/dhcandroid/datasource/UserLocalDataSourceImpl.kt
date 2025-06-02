package com.dhc.dhcandroid.datasource

import androidx.datastore.core.DataStore
import com.dhc.data.UserPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserLocalDataSourceImpl @Inject constructor(
    private val userProtoDataStore: DataStore<UserPreferences>
): UserLocalDataSource {
    val userPreferences: Flow<UserPreferences> = userProtoDataStore.data

    override suspend fun setUUID(uuid: String) {
        userProtoDataStore.updateData { pref ->
            pref.toBuilder().setUuid(uuid).build()
        }
    }

    override suspend fun getUUID(): Flow<String?> {
        return  return userPreferences.map { it.uuid }
    }

    override suspend fun setFcmToken(token: String) {
        userProtoDataStore.updateData { pref ->
            pref.toBuilder().setFcmToken(token).build()
        }
    }

    override suspend fun getFcmToken(): Flow<String?> {
        return userPreferences.map { it.fcmToken }
    }
}