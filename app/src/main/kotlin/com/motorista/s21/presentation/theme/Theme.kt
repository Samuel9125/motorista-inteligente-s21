package com.motorista.s21.presentation.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

/**
 * Esquema de cores para o tema claro
 */
private val LightColors = androidx.compose.material3.lightColorScheme(
    primary = md_theme_primary,
    onPrimary = md_theme_on_primary,
    primaryContainer = md_theme_primary_container,
    onPrimaryContainer = md_theme_on_primary_container,
    secondary = md_theme_secondary,
    onSecondary = md_theme_on_secondary,
    secondaryContainer = md_theme_secondary_container,
    onSecondaryContainer = md_theme_on_secondary_container,
    tertiary = md_theme_tertiary,
    onTertiary = md_theme_on_tertiary,
    tertiaryContainer = md_theme_tertiary_container,
    onTertiaryContainer = md_theme_on_tertiary_container,
    error = md_theme_error,
    onError = md_theme_on_error,
    errorContainer = md_theme_error_container,
    onErrorContainer = md_theme_on_error_container,
    background = md_theme_background,
    onBackground = md_theme_on_background,
    surface = md_theme_surface,
    onSurface = md_theme_on_surface,
    surfaceVariant = md_theme_surface_variant,
    onSurfaceVariant = md_theme_on_surface_variant,
    outline = md_theme_outline,
    inverseSurface = md_theme_on_background,
    inverseOnSurface = md_theme_background,
    inversePrimary = md_theme_primary_dark,
    surfaceTint = md_theme_primary,
    outlineVariant = md_theme_outline
)

/**
 * Esquema de cores para o tema escuro
 */
private val DarkColors = androidx.compose.material3.darkColorScheme(
    primary = md_theme_primary_dark,
    onPrimary = md_theme_on_primary_dark,
    primaryContainer = md_theme_primary_container_dark,
    onPrimaryContainer = md_theme_on_primary_container_dark,
    secondary = md_theme_secondary_dark,
    onSecondary = md_theme_on_secondary_dark,
    secondaryContainer = md_theme_secondary_container_dark,
    onSecondaryContainer = md_theme_on_secondary_container_dark,
    tertiary = md_theme_tertiary_dark,
    onTertiary = md_theme_on_tertiary_dark,
    tertiaryContainer = md_theme_tertiary_container_dark,
    onTertiaryContainer = md_theme_on_tertiary_container_dark,
    error = md_theme_error_dark,
    onError = md_theme_on_error_dark,
    errorContainer = md_theme_error_container_dark,
    onErrorContainer = md_theme_on_error_container_dark,
    background = md_theme_background_dark,
    onBackground = md_theme_on_background_dark,
    surface = md_theme_surface_dark,
    onSurface = md_theme_on_surface_dark,
    surfaceVariant = md_theme_surface_variant_dark,
    onSurfaceVariant = md_theme_on_surface_variant_dark,
    outline = md_theme_outline_dark,
    inverseSurface = md_theme_on_background_dark,
    inverseOnSurface = md_theme_background_dark,
    inversePrimary = md_theme_primary,
    surfaceTint = md_theme_primary_dark,
    outlineVariant = md_theme_outline_dark
)

/**
 * Tema principal do Motorista Inteligente S21
 *
 * Suporta:
 * - Tema claro
 * - Tema escuro
 * - Tema dinâmico (Android 12+)
 */
@Composable
fun MotoristTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (useDarkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        useDarkTheme -> DarkColors
        else -> LightColors
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
