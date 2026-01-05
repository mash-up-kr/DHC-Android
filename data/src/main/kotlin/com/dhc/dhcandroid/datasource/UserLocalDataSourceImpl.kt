package com.dhc.dhcandroid.datasource

import androidx.datastore.core.DataStore
import com.dhc.data.UserPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserLocalDataSourceImpl @Inject constructor(
    private val userProtoDataStore: DataStore<UserPreferences>,
) : UserLocalDataSource {
    override val isShownFortunePopup: Flow<Boolean> =
        userProtoDataStore.data.map { it.isShownFortunePopup }

    override suspend fun setShownFortunePopup(shown: Boolean) {
        userProtoDataStore.updateData { pref ->
            pref.toBuilder().setIsShownFortunePopup(shown).build()
        }
    }

    override suspend fun clear() {
        userProtoDataStore.updateData { pref ->
            pref.toBuilder().clear().build()
        }
    }
}