package com.dhc.dhcandroid

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dhc.dhcandroid.repository.UserDataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userDataStoreRepository: UserDataStoreRepository,
) : ViewModel() {

    /**
     * 기존에 서버에서 발급한 UserToken이 있는지 여부에 따라 저장
     */
    fun checkUserId(uuid: String) {
        viewModelScope.launch {
            userDataStoreRepository.setUUID(uuid)
        }
    }

    fun setFcmToken(token: String) {
        viewModelScope.launch {
            userDataStoreRepository.setFcmToken(token)
        }
    }
}
