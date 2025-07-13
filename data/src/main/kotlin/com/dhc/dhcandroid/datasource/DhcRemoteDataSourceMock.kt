package com.dhc.dhcandroid.datasource

import com.dhc.dhcandroid.model.AnalysisViewResponse
import com.dhc.dhcandroid.model.CalendarViewResponse
import com.dhc.dhcandroid.model.EndTodayMissionRequest
import com.dhc.dhcandroid.model.EndTodayMissionResponse
import com.dhc.dhcandroid.model.FortuneResponse
import com.dhc.dhcandroid.model.HomeViewResponse
import com.dhc.dhcandroid.model.MissionCategoriesResponse
import com.dhc.dhcandroid.model.MissionsResponse
import com.dhc.dhcandroid.model.MyPageResponse
import com.dhc.dhcandroid.model.RegisterUserResponse
import com.dhc.dhcandroid.model.SearchUserByTokenResponse
import com.dhc.dhcandroid.model.ToggleMissionRequest
import com.dhc.dhcandroid.model.UserProfile
import retrofit2.Response
import javax.inject.Inject

class DhcRemoteDataSourceMock @Inject constructor(
): DhcRemoteDataSource {
    override suspend fun searchUserByToken(userToken: String): Response<SearchUserByTokenResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun registerUser(userProfile: UserProfile): Response<RegisterUserResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun changeMissionStatus(
        userId: String,
        missionId: String,
        toggleMissionRequest: ToggleMissionRequest,
    ): Response<MissionsResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun requestFinishTodayMissions(
        userId: String,
        endTodayMissionRequest: EndTodayMissionRequest,
    ): Response<EndTodayMissionResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteUser(userId: String): Response<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun getHomeView(userId: String): Response<HomeViewResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun getMyPageView(userId: String): Response<MyPageResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun getAnalysisView(userId: String): Response<AnalysisViewResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun getMissionCategories(): Response<MissionCategoriesResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun getCalendarView(
        userId: String,
        yearMonth: String,
    ): Response<CalendarViewResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun getDailyFortune(userId: String, date: String): Response<FortuneResponse> {
        TODO("Not yet implemented")
    }
}
