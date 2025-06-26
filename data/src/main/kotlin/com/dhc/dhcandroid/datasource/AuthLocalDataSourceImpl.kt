package com.dhc.dhcandroid.datasource

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Context
import android.provider.Settings
import android.util.Log
import androidx.datastore.core.DataStore
import com.dhc.data.UserPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AuthLocalDataSourceImpl @Inject constructor(
    private val userProtoDataStore: DataStore<UserPreferences>,
    @ApplicationContext private val context: Context,
): AuthLocalDataSource {
    val userPreferences: Flow<UserPreferences> = userProtoDataStore.data

    override suspend fun setUUID() {
        val uuid = userPreferences.firstOrNull()?.uuid
        if(uuid.isNullOrEmpty()) {
            userProtoDataStore.updateData { pref ->
                pref.toBuilder().setUuid(getUUIDFromDevice()).build()
            }
        }
    }

    @SuppressLint("HardwareIds")
    private fun getUUIDFromDevice(): String? {
        val userId = Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
        Log.d(TAG, "UUID from Device: $userId")
        return userId
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
