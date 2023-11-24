package kz.veter420.android_modern.presentation.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

object Colors {
    val BackgroundPrimaryDark = Color(0xFF010101)
    val BackgroundPrimaryLight = Color(0xFFFEFEFE)
    val BackgroundSecondaryDark = Color(0xFF1C1C1E)
    val BackgroundSecondaryLight = Color(0xFFE3E3E3)

    val ElementStroke = Color(0xFF4D4D4D)

    val TextPrimary = Color(0xFFEDEDED)
    val TextSecondary = Color(0xFFA9A9A9)

    val PrimaryMain = Color(0xFFF8C141)
    val PrimaryGradientStart = Color(0xFFF8C141)
    val PrimaryGradientEnd = Color(0xFFFF9700)

    val SystemSuccess = Color(0xFF69DB7C)
    val SystemError = Color(0xFFE64646)

    val Gradient = Brush.horizontalGradient(
        listOf(PrimaryGradientStart, PrimaryGradientEnd)
    )
}

