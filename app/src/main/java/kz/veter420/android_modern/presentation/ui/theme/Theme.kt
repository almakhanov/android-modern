package kz.veter420.android_modern.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    primary = Colors.PrimaryMain,
    primaryVariant = Colors.PrimaryMain,
    secondary = Colors.PrimaryMain,

    background = Colors.BackgroundPrimaryDark,
    surface = Color.White,

    onBackground = Color.White,
    onSurface = Color.White,

    onPrimary = Color.White,
    onSecondary = Color.White,
)

private val LightColorPalette = lightColors(
    primary = Colors.PrimaryMain,
    primaryVariant = Colors.PrimaryMain,
    secondary = Colors.PrimaryMain,

    background = Colors.BackgroundPrimaryLight,
    surface = Color.Black,

    onBackground = Color.Black,
    onSurface = Color.Black,

    onPrimary = Color.Black,
    onSecondary = Color.Black,
)

@Composable
fun MainTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    val systemUiController = rememberSystemUiController()
    if (darkTheme) {
        systemUiController.setSystemBarsColor(color = Colors.BackgroundPrimaryDark)
    } else {
        systemUiController.setSystemBarsColor(color = Colors.BackgroundPrimaryLight)
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}