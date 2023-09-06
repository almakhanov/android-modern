package kz.veter420.android_modern.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kz.veter420.android_modern.presentation.ui.theme.Colors

@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    currentScreenRoute: String?,
    onItemClick: (BottomNavItem) -> Unit
) {
    BottomNavigation(
        modifier = Modifier.background(color = Colors.BackgroundPrimaryDark),
        backgroundColor = Colors.BackgroundPrimaryDark,
        contentColor = Colors.TextSecondary,
        elevation = 5.dp
    ) {
        items.forEach { item ->
            val selected = item.route == currentScreenRoute
            BottomNavigationItem(
                selected = selected,
                onClick = { onItemClick(item) },
                icon = {
                    BottomNavigationIcon(
                        name = item.name,
                        icon = item.icon,
                        selected = selected,
                        badgeCount = item.badgeCount,
                    )
                }
            )
        }
    }
}