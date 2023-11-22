package kz.veter420.android_modern.presentation.pages.products

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kz.veter420.android_modern.data.dto.base.AsyncResult
import kz.veter420.android_modern.domain.use_case.GetProductsUseCase
import kz.veter420.android_modern.presentation.pages.alert.AlertData
import kz.veter420.android_modern.presentation.base.BaseViewModel
import kz.veter420.android_modern.presentation.base.Reducer


class ProductViewModel(
	private val getProductsUseCase: GetProductsUseCase
) : BaseViewModel<ProductContract.State, ProductContract.Event>() {

	private val reducer = ReducerImpl(ProductContract.State())

	override val state: StateFlow<ProductContract.State> get() = reducer.state

	init {
		updateProducts()
	}

	fun updateProducts(
		isRefreshing: Boolean = false
	) {
		if (isRefreshing) {
			reducer.sendEvent(ProductContract.Event.Refresh)
		}
		reducer.sendEvent(ProductContract.Event.GetProducts)
	}


	private inner class ReducerImpl(
		initial: ProductContract.State
	) : Reducer<ProductContract.State, ProductContract.Event>(initial) {
		override fun reduce(oldState: ProductContract.State, event: ProductContract.Event) {
			when (event) {
				ProductContract.Event.Refresh -> setState(
					oldState.copy(
						refreshing = true,
						error = null
					)
				)

				ProductContract.Event.GetProducts -> {
					getProductsUseCase.invoke().onEach { result ->
						when (result) {
							is AsyncResult.Success -> {
								setState(
									oldState.copy(
										data = result.data,
										loading = false,
										refreshing = false,
										error = null
									)
								)
							}

							is AsyncResult.Error -> {
								setState(
									oldState.copy(
										error = AlertData(result.error),
										loading = false,
										refreshing = false,
									)
								)
							}
						}
					}.launchIn(viewModelScope)
				}
			}
		}
	}
}
