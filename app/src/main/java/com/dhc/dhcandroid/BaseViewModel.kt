package com.dhc.dhcandroid

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dhc.dhcandroid.mvi.UiEvent
import com.dhc.dhcandroid.mvi.UiSideEffect
import com.dhc.dhcandroid.mvi.UiState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


abstract class BaseViewModel<State: UiState, Event: UiEvent, SideEffect: UiSideEffect>: ViewModel() {

    private val initialUiState: State by lazy { createInitialState() }
    protected abstract fun createInitialState(): State
    protected abstract suspend fun handleEvent(event: Event)

    private val _state = MutableStateFlow(initialUiState)
    val state = _state.asStateFlow()

    private val _sideEffect: Channel<SideEffect> = Channel()
    val sideEffect = _sideEffect.receiveAsFlow()

    protected val currentUiState: State
        get() = state.value

    /**
     * action이 발생하면 event 전달
     */
    fun sendEvent(event: Event) {
        viewModelScope.launch {
            handleEvent(event)
        }
    }

    /**
     * reduce : 상태 값 Update
     */
    protected fun reduce(reduce: State.() -> State) {
        _state.update(reduce)
    }

    /**
     * SideEffect가 발생하면 이벤트 전달
     */
    protected fun postSideEffect(sideEffect: SideEffect) {
        viewModelScope.launch { _sideEffect.send(sideEffect) }
    }

    override fun onCleared() {
        super.onCleared()
        _sideEffect.close()
    }

}