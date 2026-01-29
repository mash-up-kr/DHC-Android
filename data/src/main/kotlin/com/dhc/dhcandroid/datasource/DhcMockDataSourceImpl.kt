package com.dhc.dhcandroid.datasource

import android.content.Context
import com.dhc.dhcandroid.model.AnalysisViewResponse
import com.dhc.dhcandroid.model.CalendarViewResponse
import com.dhc.dhcandroid.model.CreateShareTokenResponse
import com.dhc.dhcandroid.model.EndTodayMissionRequest
import com.dhc.dhcandroid.model.EndTodayMissionResponse
import com.dhc.dhcandroid.model.FortuneResponse
import com.dhc.dhcandroid.model.HomeViewResponse
import com.dhc.dhcandroid.model.MissionCategoriesResponse
import com.dhc.dhcandroid.model.MissionsResponse
import com.dhc.dhcandroid.model.MyPageResponse
import com.dhc.dhcandroid.model.RegisterUserResponse
import com.dhc.dhcandroid.model.RewardProgressResponse
import com.dhc.dhcandroid.model.SearchUserByTokenResponse
import com.dhc.dhcandroid.model.ToggleMissionRequest
import com.dhc.dhcandroid.model.UserProfile
import com.dhc.dhcandroid.model.YearlyFortuneResponse
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.serialization.json.Json
import retrofit2.Response
import javax.inject.Inject

class DhcMockDataSourceImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) : DhcRemoteDataSource {
    private val json = Json {
        coerceInputValues = true
        ignoreUnknownKeys = true
        encodeDefaults = true
    }

    override suspend fun searchUserByToken(userToken: String): Response<SearchUserByTokenResponse> =
        createResponseFromAsset<SearchUserByTokenResponse>("mock_search_user_by_token_response.json")

    override suspend fun registerUser(userProfile: UserProfile): Response<RegisterUserResponse> =
        createResponseFromAsset<RegisterUserResponse>("mock_register_user_response.json")

    override suspend fun changeMissionStatus(
        userId: String,
        missionId: String,
        toggleMissionRequest: ToggleMissionRequest,
    ): Response<MissionsResponse> =
        createResponseFromAsset<MissionsResponse>("mock_missions_response.json")

    override suspend fun requestFinishTodayMissions(
        userId: String,
        endTodayMissionRequest: EndTodayMissionRequest,
    ): Response<EndTodayMissionResponse> =
        createResponseFromAsset<EndTodayMissionResponse>("mock_end_today_mission_response.json")

    override suspend fun deleteUser(userId: String): Response<Unit> =
        Response.success(Unit)

    override suspend fun getHomeView(userId: String): Response<HomeViewResponse> =
        createResponseFromAsset<HomeViewResponse>("mock_home_view_response.json")

    override suspend fun getMyPageView(userId: String): Response<MyPageResponse> =
        createResponseFromAsset<MyPageResponse>("mock_my_page_response.json")

    override suspend fun getAnalysisView(userId: String): Response<AnalysisViewResponse> =
        createResponseFromAsset<AnalysisViewResponse>("mock_analysis_view_response.json")

    override suspend fun getMissionCategories(): Response<MissionCategoriesResponse> =
        createResponseFromAsset<MissionCategoriesResponse>("mock_mission_categories_response.json")

    override suspend fun getCalendarView(
        userId: String,
        yearMonth: String,
    ): Response<CalendarViewResponse> =
        createResponseFromAsset<CalendarViewResponse>("mock_calendar_view_response.json")

    override suspend fun getDailyFortune(userId: String, date: String): Response<FortuneResponse> =
        createResponseFromAsset<FortuneResponse>("mock_fortune_response.json")

    override suspend fun updateEasterEggHistory(userId: String): Response<Unit> =
        Response.success(Unit)

    override suspend fun getRewardProgress(userId: String): Response<RewardProgressResponse> =
        createResponseFromAsset<RewardProgressResponse>("mock_reward_progress_response.json")

    override suspend fun createShareToken(userId: String): Response<CreateShareTokenResponse> =
        createResponseFromAsset<CreateShareTokenResponse>("mock_create_share_token.json")

    override suspend fun getYearlyFortune(userId: String): Response<YearlyFortuneResponse> =
        createResponseFromAsset<YearlyFortuneResponse>("mock_yearly_fortune_response.json")

    private inline fun <reified T> createResponseFromAsset(fileName: String): Response<T> = Response.success(
        json.decodeFromString<T>(
            context.assets.open(fileName).bufferedReader().use { it.readText() }
        )
    )
}
