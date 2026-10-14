package com.example.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = CyberCyan,
    onPrimary = VoidBlack,
    primaryContainer = CyberCyanDark,
    onPrimaryContainer = CyberCyan,
    secondary = EntityViolet,
    onSecondary = Color.White,
    secondaryContainer = EntityVioletDark,
    onSecondaryContainer = EntityVioletLight,
    tertiary = TorajoOrange,
    onTertiary = Color.White,
    background = VoidBlack,
    onBackground = TextWhite,
    surface = SurfaceDark,
    onSurface = TextWhite,
    surfaceVariant = SurfaceContainerDark,
    onSurfaceVariant = TextMuted,
    outline = CardBorder,
    error = GlitchRed
)

@Composable
fun AlgorithomusTheme(
    content: @Composable () -> Unit
) {
    // Algorithomus is inherently a dark, cinematic glitch-universe atmosphere
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = Typography,
        content = content
    )
}
