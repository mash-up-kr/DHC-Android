package com.dhc.dhcandroid.repository

import com.dhc.common.DhcResult
import com.dhc.dhcandroid.datasource.DhcRemoteDataSource
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
import com.dhc.dhcandroid.util.runDhcCatching
import javax.inject.Inject

class DhcRepositoryImpl @Inject constructor(
    private val dhcRemoteDataSource: DhcRemoteDataSource,
) : DhcRepository {
    override suspend fun registerUser(userProfile: UserProfile): DhcResult<RegisterUserResponse> =
        runDhcCatching { dhcRemoteDataSource.registerUser(userProfile) }

    override suspend fun changeMissionStatus(
        userId: String,
        missionId: String,
        toggleMissionRequest: ToggleMissionRequest
    ): DhcResult<MissionsResponse> =
        runDhcCatching {
            dhcRemoteDataSource.changeMissionStatus(
                userId,
                missionId,
                toggleMissionRequest
            )
        }

    override suspend fun requestFinishTodayMissions(endTodayMissionRequest: EndTodayMissionRequest): DhcResult<EndTodayMissionResponse> =
        runDhcCatching { dhcRemoteDataSource.requestFinishTodayMissions(endTodayMissionRequest) }

    override suspend fun requestLogOutUser(userId: String): DhcResult<LogoutResponse> =
        runDhcCatching { dhcRemoteDataSource.requestLogOutUser(userId) }

    override suspend fun getHomeView(userId: String): DhcResult<HomeViewResponse> =
        runDhcCatching { dhcRemoteDataSource.getHomeView(userId) }

    override suspend fun getMyPageView(userId: String): DhcResult<MyPageResponse> =
        runDhcCatching { dhcRemoteDataSource.getMyPageView(userId) }

    override suspend fun getAnalysisView(userId: String): DhcResult<AnalysisViewResponse> =
        runDhcCatching { dhcRemoteDataSource.getAnalysisView(userId) }

    override suspend fun getMissionCategories(): DhcResult<MissionCategoriesResponse> =
        runDhcCatching { dhcRemoteDataSource.getMissionCategories() }
}
