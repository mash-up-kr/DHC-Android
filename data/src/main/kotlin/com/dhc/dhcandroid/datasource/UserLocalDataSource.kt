package com.dhc.dhcandroid.datasource

import kotlinx.coroutines.flow.Flow

interface UserLocalDataSource {
    val isShownFortunePopup: Flow<Boolean>
    val hasSeenLoveMission: Flow<Boolean>

    suspend fun setShownFortunePopup(shown: Boolean)
    suspend fun setHasSeenLoveMission(seen: Boolean)
    suspend fun clear()
}