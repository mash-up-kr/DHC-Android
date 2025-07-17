package com.dhc.dhcandroid.datasource

import androidx.datastore.core.DataStore
import com.example.datastore.EggPreferences
import kotlinx.coroutines.flow.firstOrNull
import java.time.LocalDate
import javax.inject.Inject

class EasterEggDataSourceImpl @Inject constructor(
    private val eggProtoDataStore: DataStore<EggPreferences>,
) : EasterEggDataSource {
    override suspend fun setBirthDay(birthDay: LocalDate) {
        eggProtoDataStore.updateData { pref ->
            pref.toBuilder()
                .setEpochDay(birthDay.toEpochDay())
                .build()
        }
    }

    override suspend fun getBirthDay(): LocalDate? {
        return eggProtoDataStore.data.firstOrNull()?.epochDay?.let {
            LocalDate.ofEpochDay(it)
        }
    }
}
