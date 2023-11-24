package kz.veter420.android_modern.presentation.app

import android.annotation.SuppressLint
import android.content.Intent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink
import kz.veter420.android_modern.R
import kz.veter420.android_modern.presentation.navigation.BottomNavItem
import kz.veter420.android_modern.presentation.navigation.BottomNavigationBar
import kz.veter420.android_modern.presentation.navigation.Destinations
import kz.veter420.android_modern.presentation.pages.post.PostScreen
import kz.veter420.android_modern.presentation.pages.products.ProductScreen
import kz.veter420.android_modern.presentation.pages.products.detail.ProductDetailScreen
import kz.veter420.android_modern.presentation.pages.calc.CalcScreen
import kz.veter420.android_modern.presentation.ui.theme.MainTheme


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MviApp(navController: NavHostController = rememberNavController()) {

	val navItems = mutableListOf<BottomNavItem>().apply {
		add(
			BottomNavItem(
				name = stringResource(id = R.string.posts),
				route = Destinations.Post.route,
				icon = painterResource(id = R.drawable.ic_newspaper)
			)
		)
		add(
			BottomNavItem(
				name = stringResource(id = R.string.products),
				route = Destinations.Products.route,
				icon = painterResource(id = R.drawable.ic_home)
			)
		)
		add(
			BottomNavItem(
				name = stringResource(id = R.string.calc),
				route = Destinations.Calculator.route,
				icon = painterResource(id = R.drawable.ic_calculate)
			)
		)
	}

	MainTheme {

		val backStackEntry = navController.currentBackStackEntryAsState()
		val currentScreenRoute = backStackEntry.value?.destination?.route
		val bottomNavVisible = navItems.any {
			it.route == currentScreenRoute
		}

		Scaffold(bottomBar = {
			AnimatedVisibility(
				visible = bottomNavVisible,
				enter = slideInVertically { it },
				exit = slideOutVertically { it },
			) {
				BottomNavigationBar(
					items = navItems,
					currentScreenRoute = currentScreenRoute
				) {
					if (it.route != currentScreenRoute) {
						navController.navigate(it.route)
					}
				}
			}
		}) {
			NavHost(
				navController = navController,
				startDestination = Destinations.Post.route,
				enterTransition = {
					fadeIn(animationSpec = tween(200))
				},
				exitTransition = {
					fadeOut(animationSpec = tween(200))
				}
			) {
				composable(route = Destinations.Products.route) {
					ProductScreen(navController)
				}
				composable(route = Destinations.Post.route) {
					PostScreen(navController)
				}
				composable(route = Destinations.Calculator.route) {
					CalcScreen(navController)
				}
				composable(
					route = Destinations.ProductDetailPage().route,
					deepLinks = listOf(
						navDeepLink {
							uriPattern = "modern://android/" + Destinations.ProductDetailPage().route
							action = Intent.ACTION_VIEW
						}
					)
				) { entry ->
					val productId = entry.arguments?.getString(
						Destinations.ProductDetailPage().id
					)?.toIntOrNull() ?: 0
					ProductDetailScreen(navController, productId)
				}
			}
		}
	}
}