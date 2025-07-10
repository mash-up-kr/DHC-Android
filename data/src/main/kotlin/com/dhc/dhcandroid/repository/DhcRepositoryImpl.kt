package com.dhc.dhcandroid.repository

import com.dhc.common.DhcResult
import com.dhc.common.FormatterUtil.dhcYearMonthDayFormat
import com.dhc.common.FormatterUtil.dhcYearMonthFormat
import com.dhc.common.getSuccessOrNull
import com.dhc.dhcandroid.datasource.DhcRemoteDataSource
import com.dhc.dhcandroid.model.AnalysisMonthViewResponse
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
import com.dhc.dhcandroid.util.runDhcCatching
import java.time.LocalDate
import javax.inject.Inject

class DhcRepositoryImpl @Inject constructor(
    private val dhcRemoteDataSource: DhcRemoteDataSource,
) : DhcRepository {

    private val cachedCalendarView = mutableMapOf<LocalDate, AnalysisMonthViewResponse>()

    override suspend fun searchUserByToken(userToken: String): DhcResult<SearchUserByTokenResponse> =
        runDhcCatching { dhcRemoteDataSource.searchUserByToken(userToken) }

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

    override suspend fun requestFinishTodayMissions(userId: String, endTodayMissionRequest: EndTodayMissionRequest): DhcResult<EndTodayMissionResponse> =
        runDhcCatching { dhcRemoteDataSource.requestFinishTodayMissions(userId, endTodayMissionRequest) }

    override suspend fun deleteUser(userId: String): DhcResult<Unit> =
        runDhcCatching { dhcRemoteDataSource.deleteUser(userId) }

    override suspend fun getHomeView(userId: String): DhcResult<HomeViewResponse> =
        runDhcCatching { dhcRemoteDataSource.getHomeView(userId) }

    override suspend fun getMyPageView(userId: String): DhcResult<MyPageResponse> =
        runDhcCatching { dhcRemoteDataSource.getMyPageView(userId) }

    override suspend fun getAnalysisView(userId: String): DhcResult<AnalysisViewResponse> =
        runDhcCatching { dhcRemoteDataSource.getAnalysisView(userId) }

    override suspend fun getMissionCategories(): DhcResult<MissionCategoriesResponse> =
        runDhcCatching { dhcRemoteDataSource.getMissionCategories() }

    override suspend fun getCalendarView(
        userId: String,
        yearMonth: LocalDate,
    ): DhcResult<CalendarViewResponse> {
        val prevMonth = yearMonth.plusMonths(-1L)
        val nextMonth = yearMonth.plusMonths(1L)
        val targetMonthList = listOf(prevMonth, yearMonth, nextMonth)

        if (targetMonthList.all { cachedCalendarView.containsKey(it) }) {
            return DhcResult.Success(
                CalendarViewResponse(
                    threeMonthViewResponse = targetMonthList.map {
                        cachedCalendarView[it] ?: AnalysisMonthViewResponse()
                    }
                )
            )
        }

        val result = runDhcCatching {
            dhcRemoteDataSource.getCalendarView(
                userId,
                yearMonth.format(dhcYearMonthFormat)
            )
        }

        result.getSuccessOrNull()
            ?.threeMonthViewResponse
            ?.forEachIndexed { index, analysisMonthViewResponse ->
                if (index < targetMonthList.size) {
                    cachedCalendarView[targetMonthList[index]] = analysisMonthViewResponse
                }
            }
        return result
    }

    override suspend fun getDailyFortune(
        userId: String,
        date: LocalDate,
    ): DhcResult<FortuneResponse> {
        return runDhcCatching {
            dhcRemoteDataSource.getDailyFortune(userId, date.format(dhcYearMonthDayFormat))
        }
    }
}
