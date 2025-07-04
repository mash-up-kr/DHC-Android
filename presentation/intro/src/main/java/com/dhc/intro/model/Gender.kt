package com.dhc.intro.model

import androidx.annotation.StringRes
import com.dhc.intro.R
import com.dhc.dhcandroid.model.Gender as ServerGender

enum class Gender(@StringRes val textResourceId: Int) {
    MALE(R.string.male),
    FEMALE(R.string.female),
    ;

    fun toServerType() = when(this) {
        MALE -> ServerGender.MALE
        FEMALE -> ServerGender.FEMALE
    }
}
