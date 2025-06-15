package com.dhc.common

import androidx.compose.foundation.clickable
import androidx.compose.ui.Modifier

fun Modifier.clickableIf(predicate: () -> Boolean, onClick: () -> Unit) =
    if (predicate()) this.then(Modifier.clickable(onClick = onClick)) else this
