package com.dhc.dhcandroid.repository

import com.dhc.dhcandroid.model.BirthDate
import com.dhc.dhcandroid.model.Gender
import com.dhc.dhcandroid.model.MissionCategory

interface UserRepository {
    fun updateGender(gender: Gender)
    fun updateBirthDay(birthDate: BirthDate)
    fun updateBirthTime(birthTime: String?)
    fun updateCategory(categoryList: List<MissionCategory>)
    fun updateUserProfile()
}
