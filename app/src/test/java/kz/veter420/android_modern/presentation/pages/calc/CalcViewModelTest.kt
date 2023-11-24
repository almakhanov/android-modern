package kz.veter420.android_modern.presentation.pages.calc

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Test


class CalcViewModelTest {

	private val viewModel = CalcViewModel()

	@Test
	fun `if input is valid return true`() {
		listOf(
			Pair("0", "0"),
			Pair("123", "123"),
			Pair("-1", "-1"),
			Pair("1", "2"),
			Pair("99999", "99999"),
		).forEach {
			assertTrue(viewModel.isValid(it.first, it.second))
		}
	}

	@Test
	fun `if input is invalid return false`() {
		listOf(
			Pair("", "456"),
			Pair("123", ""),
			Pair("", "")
		).forEach {
			assertFalse(viewModel.isValid(it.first, it.second))
		}
	}

	@Test
	fun testPositiveNumbers() {
		val result = viewModel.multiply(4, 2)
		assertEquals(8, result)
	}

	@Test
	fun testNegativeNumbers() {
		val result = viewModel.multiply(-4, -2)
		assertEquals(8, result)
	}

	@Test
	fun testZero() {
		val result = viewModel.multiply(0, 5)
		assertEquals(0, result)
	}

	@Test
	fun testOneNegativeOnePositive() {
		val result = viewModel.multiply(-4, 2)
		assertEquals(-8, result)
	}

}