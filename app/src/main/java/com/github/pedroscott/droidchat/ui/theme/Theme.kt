package com.github.pedroscott.droidchat.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = ChatColors.Turquoise80,
    onPrimary = Color.White,
    primaryContainer = ChatColors.Turquoise30,
    onPrimaryContainer = ChatColors.Turqouise90,
    secondary = ChatColors.Green80,
    onSecondary = ChatColors.Green20,
    tertiary = ChatColors.Wildfire80,
    onTertiary = ChatColors.Wildfire20,
    surface = ChatColors.Neutral10,
    onSurface = ChatColors.Neutral90,
    onSurfaceVariant = ChatColors.Neutral60,
    inverseSurface = Color.White,
    inverseOnSurface = Color.Black,
    error = ChatColors.ColorError
)

private val LightColorScheme = lightColorScheme(
    primary = ChatColors.Turquoise80,
    onPrimary = Color.White,
    primaryContainer = ChatColors.Turqouise90,
    onPrimaryContainer = ChatColors.Turqouise10,
    secondary = ChatColors.Green40,
    onSecondary = Color.Black,
    tertiary = ChatColors.Wildfire90,
    onTertiary = Color.Black,
    surface = ChatColors.Surface,
    onSurface = ChatColors.Neutral10,
    onSurfaceVariant = ChatColors.Neutral60,
    inverseSurface = Color.Black,
    inverseOnSurface = Color.White,
    error = ChatColors.ColorError
)

@Composable
fun DroidChatTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}