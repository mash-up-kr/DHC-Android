package com.dhc.intro.model

import androidx.annotation.StringRes
import com.dhc.intro.R

enum class CalendarType(@StringRes val textResourceId: Int) {
    SOLAR(R.string.calendar_solar),
    LUNAR(R.string.calendar_lunar),
}
