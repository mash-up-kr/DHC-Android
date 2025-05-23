package com.dhc.dhcandroid.datastore

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.dhc.data.SamplePreferences
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream

class PreferencesSerializer : Serializer<SamplePreferences> {
    override val defaultValue: SamplePreferences = SamplePreferences.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): SamplePreferences {
        return try {
            SamplePreferences.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto", exception)
        }
    }

    override suspend fun writeTo(t: SamplePreferences, output: OutputStream) = t.writeTo(output)
}
