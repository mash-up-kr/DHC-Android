package com.dhc.dhcandroid.datastore

import androidx.datastore.core.Serializer
import com.example.datastore.EggPreferences
import java.io.InputStream
import java.io.OutputStream

class PreferencesEggSerializer : Serializer<EggPreferences> {
    override val defaultValue: EggPreferences = EggPreferences.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): EggPreferences {
        return EggPreferences.parseFrom(input)
    }

    override suspend fun writeTo(t: EggPreferences, output: OutputStream) {
        t.writeTo(output)
    }
}
