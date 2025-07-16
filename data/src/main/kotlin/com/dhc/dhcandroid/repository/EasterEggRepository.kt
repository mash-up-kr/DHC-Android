package com.dhc.dhcandroid.repository

import java.time.LocalDate

interface EasterEggRepository {

    suspend fun getBirthDay(): LocalDate?

    suspend fun setBirthDay(birthDay: LocalDate)
}
