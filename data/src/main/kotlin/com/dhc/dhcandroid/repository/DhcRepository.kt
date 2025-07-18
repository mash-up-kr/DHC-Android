package com.dhc.dhcandroid.repository

import com.dhc.common.DhcResult
import com.dhc.dhcandroid.model.AnalysisViewResponse
import com.dhc.dhcandroid.model.CalendarViewResponse
import com.dhc.dhcandroid.model.EndTodayMissionRequest
import com.dhc.dhcandroid.model.EndTodayMissionResponse
import com.dhc.dhcandroid.model.FortuneResponse
import com.dhc.dhcandroid.model.HomeViewResponse
import com.dhc.dhcandroid.model.MissionsResponse
import com.dhc.dhcandroid.model.MissionCategoriesResponse
import com.dhc.dhcandroid.model.MyPageResponse
import com.dhc.dhcandroid.model.RegisterUserResponse
import com.dhc.dhcandroid.model.SearchUserByTokenResponse
import com.dhc.dhcandroid.model.ToggleMissionRequest
import com.dhc.dhcandroid.model.UserProfile
import java.time.LocalDate

interface DhcRepository {
    suspend fun searchUserByToken(
        userToken: String,
    ): DhcResult<SearchUserByTokenResponse>

    suspend fun registerUser(
        userProfile: UserProfile,
    ): DhcResult<RegisterUserResponse>

    suspend fun changeMissionStatus(
        userId: String,
        missionId: String,
        toggleMissionRequest: ToggleMissionRequest,
    ): DhcResult<MissionsResponse>

    suspend fun requestFinishTodayMissions(
        userId: String,
        endTodayMissionRequest: EndTodayMissionRequest,
    ): DhcResult<EndTodayMissionResponse>

    suspend fun deleteUser(
        userId: String,
    ): DhcResult<Unit>

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

    suspend fun getCalendarView(
        userId: String,
        yearMonth: LocalDate,
    ): DhcResult<CalendarViewResponse>

    fun clearCachedCalendarView()

    suspend fun getDailyFortune(
        userId: String,
        date: LocalDate,
    ): DhcResult<FortuneResponse>

    suspend fun updateEasterEggHistory(
        userId: String,
    ): DhcResult<Unit>
}
