package com.dhc.dhcandroid.repository

import com.dhc.dhcandroid.datasource.UserLocalDataSource
import com.dhc.dhcandroid.datasource.UserMemoryDataSource
import com.dhc.dhcandroid.model.BirthDate
import com.dhc.dhcandroid.model.Gender
import com.dhc.dhcandroid.model.MissionCategory
import com.dhc.dhcandroid.model.UserProfile
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userMemoryDataSource: UserMemoryDataSource,
    private val userLocalDataSource: UserLocalDataSource,
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

    override suspend fun getIsShownFortunePopup(): Boolean =
        userLocalDataSource.isShownFortunePopup.firstOrNull() ?: true

    override suspend fun updateIsShownFortunePopup(shown: Boolean) {
        userLocalDataSource.setShownFortunePopup(shown)
    }
}
