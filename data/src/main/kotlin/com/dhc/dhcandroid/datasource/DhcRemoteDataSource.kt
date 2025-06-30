package com.dhc.dhcandroid.datasource

import com.dhc.dhcandroid.model.AnalysisViewResponse
import com.dhc.dhcandroid.model.EndTodayMissionRequest
import com.dhc.dhcandroid.model.EndTodayMissionResponse
import com.dhc.dhcandroid.model.HomeViewResponse
import com.dhc.dhcandroid.model.LogoutResponse
import com.dhc.dhcandroid.model.MissionsResponse
import com.dhc.dhcandroid.model.MissionCategoriesResponse
import com.dhc.dhcandroid.model.MyPageResponse
import com.dhc.dhcandroid.model.RegisterUserResponse
import com.dhc.dhcandroid.model.ToggleMissionRequest
import com.dhc.dhcandroid.model.UserProfile
import retrofit2.Response

interface DhcRemoteDataSource {
    suspend fun registerUser(
        userProfile: UserProfile,
    ): Response<RegisterUserResponse>

    suspend fun changeMissionStatus(
        userId: String,
        missionId: String,
        toggleMissionRequest: ToggleMissionRequest,
    ): Response<MissionsResponse>

    suspend fun requestFinishTodayMissions(
        endTodayMissionRequest: EndTodayMissionRequest,
    ): Response<EndTodayMissionResponse>

    suspend fun requestLogOutUser(
        userId: String,
    ): Response<LogoutResponse>

    suspend fun getHomeView(
        userId: String,
    ): Response<HomeViewResponse>

    suspend fun getMyPageView(
        userId: String,
    ): Response<MyPageResponse>

    suspend fun getAnalysisView(
        userId: String,
    ): Response<AnalysisViewResponse>

    suspend fun getMissionCategories(
    ): Response<MissionCategoriesResponse>
}
