package kz.veter420.android_modern.presentation.products.detail

import kz.veter420.android_modern.data.dto.response.ProductDto
import kz.veter420.android_modern.presentation.alert.AlertData
import kz.veter420.android_modern.presentation.base.UiEvent
import kz.veter420.android_modern.presentation.base.UiState


interface ProductDetailContract {
	sealed class Event : UiEvent {
		object Refresh: Event()
		data class GetProduct(val id: Int): Event()
	}
	data class State(
		val data: ProductDto? = null,
		val loading: Boolean = true,
		val error: AlertData? = null,
		val refreshing: Boolean = false
	) : UiState
}
