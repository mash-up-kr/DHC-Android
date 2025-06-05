@file:JvmName("ThemeKt")

package com.dhc.designsystem

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf


val LocalDhcColors = staticCompositionLocalOf<DhcColors> {
    error("No DhcColors provided")
}


@Composable
fun DhcTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalDhcColors provides colors
    ) {
        content()
    }
}