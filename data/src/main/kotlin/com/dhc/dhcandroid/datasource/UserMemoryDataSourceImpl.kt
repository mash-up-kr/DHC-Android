package com.dhc.dhcandroid.datasource

import com.dhc.dhcandroid.model.BirthDate
import com.dhc.dhcandroid.model.Gender
import com.dhc.dhcandroid.model.MissionCategory
import com.dhc.dhcandroid.model.UserProfile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class UserMemoryDataSourceImpl @Inject constructor(
) : UserMemoryDataSource {
    private val _userProfileState: MutableStateFlow<UserProfile> = MutableStateFlow(UserProfile.Empty)
    override val userProfileState: StateFlow<UserProfile> = _userProfileState.asStateFlow()

    override fun updateGender(gender: Gender) {
        _userProfileState.update { it.copy(gender = gender) }
    }

    override fun updateBirthDay(birthDate: BirthDate) {
        _userProfileState.update { it.copy(birthDate = birthDate) }
    }

    override fun updateBirthTime(birthTime: String?) {
        _userProfileState.update { it.copy(birthTime = birthTime) }
    }

    override fun updateCategory(categoryList: List<MissionCategory>) {
        _userProfileState.update { it.copy(preferredMissionCategoryList = categoryList) }
    }
}
