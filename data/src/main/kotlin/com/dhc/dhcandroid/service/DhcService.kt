package com.dhc.dhcandroid.service

import com.dhc.dhcandroid.model.AnalysisViewResponse
import com.dhc.dhcandroid.model.CalendarViewResponse
import com.dhc.dhcandroid.model.EndTodayMissionRequest
import com.dhc.dhcandroid.model.EndTodayMissionResponse
import com.dhc.dhcandroid.model.FortuneResponse
import com.dhc.dhcandroid.model.HomeViewResponse
import com.dhc.dhcandroid.model.LogoutResponse
import com.dhc.dhcandroid.model.MissionsResponse
import com.dhc.dhcandroid.model.MissionCategoriesResponse
import com.dhc.dhcandroid.model.MyPageResponse
import com.dhc.dhcandroid.model.RegisterUserResponse
import com.dhc.dhcandroid.model.SearchUserByTokenResponse
import com.dhc.dhcandroid.model.ToggleMissionRequest
import com.dhc.dhcandroid.model.UserProfile
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface DhcService {

    @GET("/api/users")
    suspend fun searchUserByToken(
        @Query("userToken") userToken: String,
    ): Response<SearchUserByTokenResponse>

    @POST("/api/users/register")
    suspend fun registerUser(
        @Body userProfile: UserProfile,
    ): Response<RegisterUserResponse>

    @PUT("/api/users/{userId}/missions/{missionId}")
    suspend fun changeMissionStatus(
        @Path("userId") userId: String,
        @Path("missionId") missionId: String,
        @Body toggleMissionRequest: ToggleMissionRequest,
    ): Response<MissionsResponse>

    @POST("/api/users/{userId}/done")
    suspend fun requestFinishTodayMissions(
        @Path("userId") userId: String,
        @Body endTodayMissionRequest: EndTodayMissionRequest,
    ): Response<EndTodayMissionResponse>

    @POST("/api/users/{userId}/logout")
    suspend fun requestLogOutUser(
        @Path("userId") userId: String,
    ): Response<LogoutResponse>

    @GET("/api/mission-categories")
    suspend fun getMissionCategories(
    ): Response<MissionCategoriesResponse>

    @GET("/view/users/{userId}/home")
    suspend fun getHomeView(
        @Path("userId") userId: String,
    ): Response<HomeViewResponse>

    @GET("/view/users/{userId}/myPage")
    suspend fun getMyPageView(
        @Path("userId") userId: String,
    ): Response<MyPageResponse>

    @GET("/view/users/{userId}/analysis")
    suspend fun getAnalysisView(
        @Path("userId") userId: String,
    ): Response<AnalysisViewResponse>

    @GET("/view/users/{userId}/calendar")
    suspend fun getCalendarView(
        @Path("userId") userId: String,
        @Query("yearMonth") yearMonth: String
    ): Response<CalendarViewResponse>

    @GET("/api/users/{userId}/fortune")
    suspend fun getDailyFortune(
        @Path("userId") userId: String,
        @Query("date") date: String,
    ): Response<FortuneResponse>
}
