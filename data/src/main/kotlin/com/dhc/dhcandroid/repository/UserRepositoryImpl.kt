package com.dhc.dhcandroid.repository

import com.dhc.dhcandroid.datasource.UserMemoryDataSource
import com.dhc.dhcandroid.model.BirthDate
import com.dhc.dhcandroid.model.Gender
import com.dhc.dhcandroid.model.MissionCategory
import com.dhc.dhcandroid.model.UserProfile
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userMemoryDataSource: UserMemoryDataSource,
) : UserRepository {
    override fun updateGender(gender: Gender) {
        userMemoryDataSource.updateGender(gender)
    }

    override fun updateBirthDay(birthDate: BirthDate) {
        userMemoryDataSource.updateBirthDay(birthDate)
    }

    override fun updateBirthTime(birthTime: String?) {
        userMemoryDataSource.updateBirthTime(birthTime)
    }

    override fun updateCategory(categoryList: List<MissionCategory>) {
        userMemoryDataSource.updateCategory(categoryList)
    }

    override fun getUserProfile(): UserProfile =
        userMemoryDataSource.userProfileState.value
}
