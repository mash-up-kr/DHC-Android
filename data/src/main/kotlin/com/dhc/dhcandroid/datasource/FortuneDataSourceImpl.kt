package com.dhc.dhcandroid.datasource

import androidx.datastore.core.DataStore
import com.dhc.data.UserPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FortuneDataSourceImpl @Inject constructor(
    private val userProtoDataStore: DataStore<UserPreferences>,
) : FortuneDataSource {
    private val userPreferences: Flow<UserPreferences> = userProtoDataStore.data

    override suspend fun addSeenFortune(date: Long) {
        userProtoDataStore.updateData { pref ->
            pref.toBuilder()
                .addFortuneDateSeenList(date)
                .build()
        }
    }

    override suspend fun getSeenFortuneList(): Flow<Set<Long>> =
        userPreferences.map { it.fortuneDateSeenListList.toHashSet() }
}
