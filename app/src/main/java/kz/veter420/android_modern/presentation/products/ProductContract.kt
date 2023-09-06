package kz.veter420.android_modern.presentation.products

import kz.veter420.android_modern.data.dto.response.ProductListDto
import kz.veter420.android_modern.presentation.alert.AlertData
import kz.veter420.android_modern.presentation.base.UiEvent
import kz.veter420.android_modern.presentation.base.UiState


interface ProductContract {
	sealed class Event : UiEvent {
		object Refresh: Event()
		object GetProducts: Event()
	}
	data class State(
		val data: ProductListDto? = null,
		val loading: Boolean = true,
		val error: AlertData? = null,
		val refreshing: Boolean = false
	) : UiState
}
