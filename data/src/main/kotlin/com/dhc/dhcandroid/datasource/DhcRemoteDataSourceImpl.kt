package com.dhc.dhcandroid.datasource

import com.dhc.dhcandroid.model.AnalysisViewResponse
import com.dhc.dhcandroid.model.CalendarViewResponse
import com.dhc.dhcandroid.model.EndTodayMissionRequest
import com.dhc.dhcandroid.model.EndTodayMissionResponse
import com.dhc.dhcandroid.model.HomeViewResponse
import com.dhc.dhcandroid.model.LogoutResponse
import com.dhc.dhcandroid.model.MissionsResponse
import com.dhc.dhcandroid.model.MissionCategoriesResponse
import com.dhc.dhcandroid.model.MyPageResponse
import com.dhc.dhcandroid.model.RegisterUserResponse
import com.dhc.dhcandroid.model.SearchUserByTokenResponse
import com.dhc.dhcandroid.model.ToggleMissionRequest
import com.dhc.dhcandroid.model.UserProfile
import com.dhc.dhcandroid.service.DhcService
import retrofit2.Response
import javax.inject.Inject

class DhcRemoteDataSourceImpl @Inject constructor(
    private val dhcService: DhcService,
): DhcRemoteDataSource {
    override suspend fun searchUserByToken(userToken: String): Response<SearchUserByTokenResponse> =
        dhcService.searchUserByToken(userToken)

    override suspend fun registerUser(userProfile: UserProfile): Response<RegisterUserResponse> {
        return dhcService.registerUser(userProfile)
    }

    override suspend fun changeMissionStatus(
        userId: String,
        missionId: String,
        toggleMissionRequest: ToggleMissionRequest
    ): Response<MissionsResponse> {
        return dhcService.changeMissionStatus(userId, missionId, toggleMissionRequest)
    }

    override suspend fun requestFinishTodayMissions(userId: String, endTodayMissionRequest: EndTodayMissionRequest): Response<EndTodayMissionResponse> {
        return dhcService.requestFinishTodayMissions(userId, endTodayMissionRequest)
    }

    override suspend fun requestLogOutUser(userId: String): Response<LogoutResponse> {
        return dhcService.requestLogOutUser(userId)
    }

    override suspend fun getHomeView(userId: String): Response<HomeViewResponse> {
        return dhcService.getHomeView(userId)
    }

    override suspend fun getMyPageView(userId: String): Response<MyPageResponse> {
        return dhcService.getMyPageView(userId)
    }

    override suspend fun getAnalysisView(userId: String): Response<AnalysisViewResponse> {
        return dhcService.getAnalysisView(userId)
    }

    override suspend fun getMissionCategories(): Response<MissionCategoriesResponse> {
        return dhcService.getMissionCategories()
    }
    override suspend fun getCalendarView(
        userId: String,
        yearMonth: String
    ): Response<CalendarViewResponse> {
        return dhcService.getCalendarView(userId, yearMonth)
    }
}
