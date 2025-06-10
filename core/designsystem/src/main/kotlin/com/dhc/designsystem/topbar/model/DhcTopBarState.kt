package com.dhc.designsystem.topbar.model

sealed interface DhcTopBarState {
    data object None : DhcTopBarState
    data class Basic(
        val title: String,
        val isShowBackButton: Boolean,
        val topBarPageState: TopBarPageState? = null,
    ) : DhcTopBarState
}
