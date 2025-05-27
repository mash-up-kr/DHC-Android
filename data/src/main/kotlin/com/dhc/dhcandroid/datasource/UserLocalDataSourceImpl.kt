package com.dhc.dhcandroid.datasource

import androidx.datastore.core.DataStore
import com.dhc.data.UserPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

class UserLocalDataSourceImpl @Inject constructor(
    private val userProtoDataStore: DataStore<UserPreferences>
): UserLocalDataSource {
    val userPreferencesFlow: Flow<UserPreferences> = userProtoDataStore.data

    override suspend fun setUUID(uuid: String) {
        userProtoDataStore.updateData { pref ->
            pref.toBuilder().setUuid(uuid).build()
        }
    }

    override suspend fun getUUID(): String? {
        return userPreferencesFlow.firstOrNull()?.uuid
    }

    override suspend fun setFcmToken(token: String) {
        userProtoDataStore.updateData { pref ->
            pref.toBuilder().setFcmToken(token).build()
        }
    }

    override suspend fun getFcmToken(): String? {
        return userPreferencesFlow.firstOrNull()?.fcmToken
    }
}