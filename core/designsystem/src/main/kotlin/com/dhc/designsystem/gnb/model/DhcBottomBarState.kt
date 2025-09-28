package com.dhc.designsystem.gnb.model

sealed interface DhcBottomBarState {

    data object None : DhcBottomBarState

    data class BottomNavigation(
        val items: List<GnbItem>,
    ) : DhcBottomBarState}
