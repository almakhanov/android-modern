package kz.veter420.android_modern.presentation.pages.profile

import kz.veter420.android_modern.presentation.base.UiEvent
import kz.veter420.android_modern.presentation.base.UiState
import kz.veter420.android_modern.presentation.pages.alert.AlertData

interface ProfileContract {
	sealed class Event : UiEvent {
	}
	data class State(
		val error: AlertData? = null,
	) : UiState
}
