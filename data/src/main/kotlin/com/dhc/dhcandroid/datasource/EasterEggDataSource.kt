package com.dhc.dhcandroid.datasource

import java.time.LocalDate

interface EasterEggDataSource {

    suspend fun setBirthDay(birthDay: LocalDate)

    suspend fun getBirthDay(): LocalDate?
}
