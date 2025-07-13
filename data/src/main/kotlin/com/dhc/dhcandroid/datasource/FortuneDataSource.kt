package com.dhc.dhcandroid.datasource

import kotlinx.coroutines.flow.Flow

interface FortuneDataSource {
    suspend fun addSeenFortune(date: Long)
    suspend fun getSeenFortuneList(): Flow<Set<Long>>
}
