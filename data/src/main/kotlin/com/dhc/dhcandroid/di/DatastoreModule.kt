package com.dhc.dhcandroid.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.dhc.data.UserPreferences
import com.dhc.dhcandroid.datastore.PreferencesEggSerializer
import com.dhc.dhcandroid.datastore.PreferencesName
import com.dhc.dhcandroid.datastore.PreferencesUserSerializer
import com.example.datastore.EggPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatastoreModule {

    @Provides
    @Singleton
    fun provideUserPreferencesSerializer(): PreferencesUserSerializer {
        return PreferencesUserSerializer()
    }

    @Provides
    @Singleton
    fun provideEggPreferencesSerializer(): PreferencesEggSerializer {
        return PreferencesEggSerializer()
    }


    @Provides
    @Singleton
    fun provideUserDatastore(
        @ApplicationContext context: Context,
        serializer: PreferencesUserSerializer,
    ): DataStore<UserPreferences> =
        DataStoreFactory.create(
            serializer = serializer,
            produceFile = { context.dataStoreFile("${PreferencesName.USER}.pb") },
        )


    @Provides
    @Singleton
    fun provideEggDatastore(
        @ApplicationContext context: Context,
        serializer: PreferencesEggSerializer,
    ): DataStore<EggPreferences> =
        DataStoreFactory.create(
            serializer = serializer,
            produceFile = { context.dataStoreFile("${PreferencesName.EGG}.pb") },
        )
}
