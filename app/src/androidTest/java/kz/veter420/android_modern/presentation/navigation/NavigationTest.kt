package kz.veter420.android_modern.presentation.navigation

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import junit.framework.TestCase.assertEquals
import kz.veter420.android_modern.presentation.app.MviApp
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class NavigationTest {

	@get:Rule
	val composeTestRule = createComposeRule()

	private lateinit var navController: TestNavHostController

	@Before
	fun setup() {
		composeTestRule.setContent {
			navController = TestNavHostController(LocalContext.current).apply {
				navigatorProvider.addNavigator(ComposeNavigator())
			}
			MviApp(navController)
		}
	}

	@Test
	fun verifyStartDestination_is_PostsScreen() {
		assertEquals(Destinations.Post.route, navController.currentBackStackEntry?.destination?.route)
	}

	@Test
	fun openProductsScreen_when_clickProductsMenuButton() {
		composeTestRule.onNodeWithText("Products").performClick()
		assertEquals(Destinations.Products.route, navController.currentBackStackEntry?.destination?.route)
	}

	@Test
	fun openCalculatorScreen_when_clickCalculatorMenuButton() {
		composeTestRule.onNodeWithText("Calculator").performClick()
		assertEquals(Destinations.Calculator.route, navController.currentBackStackEntry?.destination?.route)
	}
}
