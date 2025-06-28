package com.dhc.dhcandroid.repository

import com.dhc.common.DhcResult
import com.dhc.dhcandroid.model.AnalysisViewResponse
import com.dhc.dhcandroid.model.EndTodayMissionRequest
import com.dhc.dhcandroid.model.EndTodayMissionResponse
import com.dhc.dhcandroid.model.HomeViewResponse
import com.dhc.dhcandroid.model.LogoutResponse
import com.dhc.dhcandroid.model.Mission
import com.dhc.dhcandroid.model.MissionCategoriesResponse
import com.dhc.dhcandroid.model.MyPageResponse
import com.dhc.dhcandroid.model.RegisterUserResponse
import com.dhc.dhcandroid.model.ToggleMissionRequest
import com.dhc.dhcandroid.model.UserProfile

interface DhcRepository {
    suspend fun registerUser(
        userProfile: UserProfile,
    ): DhcResult<RegisterUserResponse>

    suspend fun changeMissionStatus(
        userId: String,
        missionId: String,
        toggleMissionRequest: ToggleMissionRequest,
    ): DhcResult<Mission>

    suspend fun requestFinishTodayMissions(
        endTodayMissionRequest: EndTodayMissionRequest,
    ): DhcResult<EndTodayMissionResponse>

    suspend fun requestLogOutUser(
        userId: String,
    ): DhcResult<LogoutResponse>

    suspend fun getHomeView(
        userId: String,
    ): DhcResult<HomeViewResponse>

    suspend fun getMyPageView(
        userId: String,
    ): DhcResult<MyPageResponse>

    suspend fun getAnalysisView(
        userId: String,
    ): DhcResult<AnalysisViewResponse>

    suspend fun getMissionCategories(
    ): DhcResult<MissionCategoriesResponse>
}
