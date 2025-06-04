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
    val colors = DhcColors(
        surface = SurfaceColor(),
        accent = AccentColor(),
        text = TextColors(
            textMain = SurfaceColor().neutral30,
            textBodyPrimary = SurfaceColor().neutral100,
            textHighLightsSecondary = AccentColor().violet200,
            textHighLightsPrimary = AccentColor().violet400
        ),
        background = BackgroundColors(
            backgroundMain = SurfaceColor().neutral900
        )
    )

    CompositionLocalProvider(
        LocalDhcColors provides colors
    ) {
        content()
    }
}