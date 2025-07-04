package com.dhc.intro.model

import androidx.annotation.StringRes
import com.dhc.intro.R
import com.dhc.dhcandroid.model.CalendarType as ServerCalendarType

enum class CalendarType(@StringRes val textResourceId: Int) {
    SOLAR(R.string.calendar_solar),
    LUNAR(R.string.calendar_lunar),
    ;

    fun toServerType(): ServerCalendarType = when (this) {
        SOLAR -> ServerCalendarType.SOLAR
        LUNAR -> ServerCalendarType.LUNAR
    }
}
