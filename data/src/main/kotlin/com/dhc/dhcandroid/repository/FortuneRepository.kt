package com.dhc.dhcandroid.repository

import kotlinx.coroutines.flow.Flow

interface FortuneRepository {
    suspend fun addSeenFortune(date: Long)
    suspend fun getSeenFortuneList(): Flow<Set<Long>>
}
