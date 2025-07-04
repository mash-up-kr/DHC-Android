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
    private val userPreferences: Flow<UserPreferences> = userProtoDataStore.data
    override suspend fun setUserId(userId: String) {
        userProtoDataStore.updateData { pref ->
            pref.toBuilder().setUserId(userId).build()
        }
    }

    override suspend fun getUserId(): Flow<String?> =
        userProtoDataStore.data.map { it.userId }

    override suspend fun setUserToken(token: String) {
        userProtoDataStore.updateData { pref ->
            pref.toBuilder().setUserToken(token).build()
        }
    }

    override suspend fun getUserToken(): Flow<String?> =
        userProtoDataStore.data.map { it.userToken }

    @SuppressLint("HardwareIds")
    override suspend fun getUUID(): String? =
        Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)

    override suspend fun setFcmToken(token: String) {
        userProtoDataStore.updateData { pref ->
            pref.toBuilder().setFcmToken(token).build()
        }
    }

    override suspend fun getFcmToken(): Flow<String?> {
        return userPreferences.map { it.fcmToken }
    }
}
