package com.dhc.dhcandroid.repository

import com.dhc.dhcandroid.datasource.EasterEggDataSource
import jakarta.inject.Inject
import java.time.LocalDate

class EasterEggRepositoryImpl @Inject constructor(
    private val eggDataSource: EasterEggDataSource,
): EasterEggRepository {
    override suspend fun getBirthDay(): LocalDate? {
        return eggDataSource.getBirthDay()
    }

    override suspend fun setBirthDay(birthDay: LocalDate) {
        eggDataSource.setBirthDay(birthDay)
    }
}
