package kz.veter420.android_modern.presentation.navigation

import android.os.Bundle
import androidx.core.net.toUri
import androidx.navigation.*

fun NavController.navigate(
    route: String,
    args: Bundle,
    navOptions: NavOptions? = null,
    navigatorExtras: Navigator.Extras? = null
) {
    val routeLink = NavDeepLinkRequest
        .Builder
        .fromUri(NavDestination.createRoute(route).toUri())
        .build()

    val deepLinkMatch = graph.matchDeepLink(routeLink)
    if (deepLinkMatch != null) {
        val destination = deepLinkMatch.destination
        val id = destination.id
        navigate(id, args, navOptions, navigatorExtras)
    } else {
        navigate(route, navOptions, navigatorExtras)
    }
}

fun NavController.navigateBack(
    destinationRoute: String
) {
    val hasBackstackTheDestinationRoute = this@navigateBack.currentBackStack.value.find {
        it.destination.route == destinationRoute
    } != null
    // if the destination is already in the backstack, simply go back
    if (hasBackstackTheDestinationRoute) {
        this.popBackStack()
    } else {
        // otherwise, navigate to a new destination popping the current destination
        this@navigateBack.navigate(destinationRoute) {
            this@navigateBack.currentBackStackEntry?.destination?.route?.let {
                popUpTo(it) {
                    inclusive = true
                }
            }
        }
    }
}