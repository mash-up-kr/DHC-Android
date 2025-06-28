package com.dhc.dhcandroid

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dhc.common.getSuccessOrNull
import com.dhc.dhcandroid.repository.AuthDataStoreRepository
import com.dhc.dhcandroid.repository.DhcRepository
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val authDataStoreRepository: AuthDataStoreRepository,
    private val dhcRepository: DhcRepository,
) : ViewModel() {

    init {
        getFcmToken()
        setUserToken()
    }

    private fun setUserToken() {
        viewModelScope.launch {
            val uuid = authDataStoreRepository.getUUID().orEmpty()
            val userToken = dhcRepository.searchUserByToken(uuid).getSuccessOrNull().orEmpty()
            authDataStoreRepository.setUserToken(userToken)
        }
    }

    fun setFcmToken(token: String) {
        viewModelScope.launch {
            authDataStoreRepository.setFcmToken(token)
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
