package com.dhc.dhcandroid

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dhc.common.getSuccessOrNull
import com.dhc.dhcandroid.navigation.DhcRoute
import com.dhc.dhcandroid.repository.AuthDataStoreRepository
import com.dhc.dhcandroid.repository.DhcRepository
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val authDataStoreRepository: AuthDataStoreRepository,
    private val dhcRepository: DhcRepository,
) : ViewModel() {

    private val _state: MutableStateFlow<MainState> = MutableStateFlow(MainState())
    val state: StateFlow<MainState> = _state.asStateFlow()

    init {
        getFcmToken()
        setUserToken()
    }

    private fun setUserToken() {
        viewModelScope.launch {
            val uuid = authDataStoreRepository.getUUID().orEmpty()
            val userId = dhcRepository.searchUserByToken(uuid).getSuccessOrNull()?.id
            if (userId.isNullOrEmpty()) {
                _state.update { it.copy(startPage = DhcRoute.INTRO) }
            } else {
                authDataStoreRepository.setUserId(userId)
                _state.update { it.copy(startPage = DhcRoute.MAIN_HOME) }
            }
        }
    }

    private fun setFcmToken(token: String) {
        viewModelScope.launch {
            authDataStoreRepository.setFcmToken(token)
        }
    }

    private fun getFcmToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(
            OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.e(TAG, "Fetching FCM registration token failed", task.exception)
                    return@OnCompleteListener
                }
                val token = task.result
                Log.d(TAG, token)
                setFcmToken(token)
            },
        )
    }

    fun triggerShowNextPage() {
        _state.update { it.copy(isShowNextPage = true) }
    }
}

data class MainState(
    val startPage: DhcRoute = DhcRoute.NONE,
    val isShowNextPage: Boolean = false,
)
