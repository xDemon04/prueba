package com.example.nurrgo.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = AppColors.Secondary,
    secondary = AppColors.Primary,
    background = AppColors.Primary, // Usando Primary en lugar de DarkBlue
    surface = AppColors.Primary,   // Usando Primary en lugar de DarkBlue
    onPrimary = AppColors.OnPrimary,
    onSecondary = AppColors.OnPrimary, // Usando OnPrimary en lugar de OnSecondary
    onBackground = AppColors.OnPrimary,
    onSurface = AppColors.OnPrimary,
    error = AppColors.Error
)

private val LightColorScheme = lightColorScheme(
    primary = AppColors.Primary,
    secondary = AppColors.Secondary,
    background = AppColors.Background,
    surface = AppColors.Surface,
    onPrimary = AppColors.OnPrimary,
    onSecondary = AppColors.OnPrimary, // Usando OnPrimary en lugar de OnSecondary
    onBackground = AppColors.OnBackground,
    onSurface = AppColors.OnSurface,
    error = AppColors.Error
)

@Composable
fun NurrgoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        shapes = AppShapes,
        content = content
    )
}
