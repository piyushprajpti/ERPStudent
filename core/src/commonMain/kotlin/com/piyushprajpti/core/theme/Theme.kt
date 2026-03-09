package com.piyushprajpti.college_erp_system.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

private val m3LightColorScheme = lightColorScheme()
private val m3DarkColorScheme = darkColorScheme()

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    // this should be used for dual theme
    // val customColorScheme = if (darkTheme) CustomDarkColorScheme else CustomLightColorScheme
    // val m3ColorScheme = if (darkTheme) m3DarkColorScheme else m3LightColorScheme

    CompositionLocalProvider(LocalCustomColorScheme provides CustomLightColorScheme) {
        MaterialTheme(
            colorScheme = m3LightColorScheme,
            typography = getTypography(),
            content = content
        )
    }
}

object CustomTheme {
    val colors: CustomColorScheme
        @Composable
        get() = LocalCustomColorScheme.current
}