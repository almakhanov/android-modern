package kz.veter420.android_modern.presentation.pages.calc

import androidx.annotation.VisibleForTesting
import kotlinx.coroutines.flow.StateFlow
import kz.veter420.android_modern.presentation.base.BaseViewModel
import kz.veter420.android_modern.presentation.base.Reducer
import kz.veter420.android_modern.presentation.pages.alert.AlertData


class CalcViewModel : BaseViewModel<CalcContract.State, CalcContract.Event>() {

	private val reducer = ReducerImpl(CalcContract.State())

	override val state: StateFlow<CalcContract.State> get() = reducer.state

	fun calculate(a: String, b: String) {
		if (isValid(a, b)) {
			reducer.sendEvent(CalcContract.Event.Calculate(a.toInt(), b.toInt()))
		} else {
			reducer.sendEvent(CalcContract.Event.InvalidParams)
		}
	}

	fun clear() {
		reducer.sendEvent(CalcContract.Event.Clear)
	}

	@VisibleForTesting
	internal fun isValid(a: String, b: String): Boolean {
		return a.isNotEmpty() && b.isNotEmpty()
	}

	@VisibleForTesting
	internal fun multiply(a: Int, b: Int): Int {
		return a * b
	}

	private inner class ReducerImpl(
		initial: CalcContract.State
	) : Reducer<CalcContract.State, CalcContract.Event>(initial) {
		override fun reduce(oldState: CalcContract.State, event: CalcContract.Event) {
			when (event) {
				CalcContract.Event.InvalidParams -> {
					val errorData = AlertData("Invalid Params")
					setState(
						oldState.copy(
							error = errorData,
							result = "?"
						)
					)
				}

				is CalcContract.Event.Calculate -> {
					val result = multiply(event.a, event.b).toString()
					setState(
						oldState.copy(
							error = null,
							result = result
						)
					)
				}

				CalcContract.Event.Clear -> {
					setState(
						oldState.copy(
							error = null,
							result = "?"
						)
					)
				}
			}
		}
	}
}
