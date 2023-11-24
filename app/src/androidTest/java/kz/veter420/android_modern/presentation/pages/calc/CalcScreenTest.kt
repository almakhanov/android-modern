package kz.veter420.android_modern.presentation.pages.calc

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class CalcScreenTest {

	@get:Rule
	val composeTestRule = createComposeRule()

	private lateinit var navController: TestNavHostController

	@Before
	fun setup() {
		composeTestRule.setContent {
			navController = TestNavHostController(LocalContext.current).apply {
				navigatorProvider.addNavigator(ComposeNavigator())
			}
			CalcScreen(navController)
		}
	}

	@Test
	fun verify_calElements_areExist() {
		composeTestRule.onNodeWithText("Calculator").assertExists()
		composeTestRule.onNodeWithText("=").assertExists()
		composeTestRule.onNodeWithText("?").assertExists()
		composeTestRule.onNodeWithText("C").assertExists()
	}

	@Test
	fun verifyCalculatorFlow() {
		composeTestRule.onNodeWithTag("inputField1").performTextInput("2")
		composeTestRule.onNodeWithTag("inputField2").performTextInput("3")
		composeTestRule.onNodeWithText("=").performClick()
		composeTestRule.onNodeWithTag("calcOutput").assertTextEquals("6")
		composeTestRule.onNodeWithText("C").performClick()
		composeTestRule.onNodeWithTag("calcOutput").assertTextEquals("?")
	}
}
