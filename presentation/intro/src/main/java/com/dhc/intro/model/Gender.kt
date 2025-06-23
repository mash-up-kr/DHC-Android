package com.dhc.intro.model

import androidx.annotation.StringRes
import com.dhc.intro.R

enum class Gender(@StringRes val textResourceId: Int) {
    MALE(R.string.male),
    FEMALE(R.string.female),
}
