package com.dhc.dhcandroid.repository

import com.dhc.dhcandroid.datasource.FortuneDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FortuneRepositoryImpl @Inject constructor(
    private val fortuneDataSource: FortuneDataSource,
) : FortuneRepository {
    override suspend fun addSeenFortune(date: Long) {
        fortuneDataSource.addSeenFortune(date)
    }

    override suspend fun getSeenFortuneList(): Flow<Set<Long>> =
        fortuneDataSource.getSeenFortuneList()
}
