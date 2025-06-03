package com.dhc.dhcandroid

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dhc.dhcandroid.repository.UserDataStoreRepository
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userDataStoreRepository: UserDataStoreRepository,
) : ViewModel() {

    init {
        getFcmToken()
        checkUserId()
    }

    /**
     * 기존에 서버에서 발급한 UserToken이 있는지 여부에 따라 저장
     */
    fun checkUserId() {
        viewModelScope.launch {
            userDataStoreRepository.setUUID()
        }
    }

    fun setFcmToken(token: String) {
        viewModelScope.launch {
            userDataStoreRepository.setFcmToken(token)
        }
    }

    fun getFcmToken() {
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
}
