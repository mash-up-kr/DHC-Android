package com.dhc.home.model

import com.dhc.dhcandroid.model.ToggleMissionRequest

enum class MissionStatusType {
    COMPLETE,
    INCOMPLETE,
    CHANGE
}

fun MissionStatusType.toToggleMissionRequest(): ToggleMissionRequest {
    return when(this) {
        MissionStatusType.COMPLETE -> ToggleMissionRequest(finished = true)
        MissionStatusType.INCOMPLETE -> ToggleMissionRequest(finished = false)
        MissionStatusType.CHANGE -> ToggleMissionRequest(switch = true)
    }
}
