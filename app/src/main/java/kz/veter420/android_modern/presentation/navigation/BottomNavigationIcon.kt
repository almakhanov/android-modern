package kz.veter420.android_modern.presentation.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.BadgedBox
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import kz.veter420.android_modern.presentation.ui.theme.Colors
import kz.veter420.android_modern.presentation.utils.setGradient

@Composable
fun BottomNavigationIcon(
    name: String,
    icon: Painter,
    selected: Boolean,
    badgeCount: Int,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        val selectedBrush = Brush.horizontalGradient(
            listOf(Colors.PrimaryGradientStart, Colors.PrimaryGradientEnd)
        )
        val unselectedBrush = Brush.horizontalGradient(
            listOf(Colors.TextSecondary, Colors.TextSecondary)
        )

        if (badgeCount > 0) {
            BadgedBox(
                badge = {
                    Text(text = badgeCount.toString())
                }
            ) {
                Icon(
                    painter = icon,
                    contentDescription = name,
                    tint = if (selected) Colors.PrimaryMain else Colors.TextSecondary
                )
            }
        } else {
            Icon(
                painter = icon,
                contentDescription = name,
                tint = if (selected) Colors.PrimaryMain else Colors.TextSecondary,
                modifier = Modifier.setGradient(if (selected) selectedBrush else unselectedBrush)
            )
        }
        Text(
            text = name,
            textAlign = TextAlign.Center,
            fontSize = 10.sp,
            fontWeight = FontWeight(500),
            color = if (selected) Colors.PrimaryMain else Colors.TextSecondary,
            modifier = Modifier.setGradient(if (selected) selectedBrush else unselectedBrush)
        )
    }
}

