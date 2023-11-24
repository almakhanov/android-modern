package kz.veter420.android_modern.presentation.pages.calc

import kz.veter420.android_modern.presentation.base.UiEvent
import kz.veter420.android_modern.presentation.base.UiState
import kz.veter420.android_modern.presentation.pages.alert.AlertData


interface CalcContract {
	sealed class Event : UiEvent {
		data class Calculate(val a: Int, val b: Int): Event()
		data object InvalidParams : Event()
		data object Clear: Event()
	}
	data class State(
		val result: String = "?",
		val error: AlertData? = null
	) : UiState
}
