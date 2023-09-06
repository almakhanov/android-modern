package kz.veter420.android_modern.presentation.products.detail

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kz.veter420.android_modern.data.dto.base.AsyncResult
import kz.veter420.android_modern.domain.use_case.GetProductByIdUseCase
import kz.veter420.android_modern.presentation.alert.AlertData
import kz.veter420.android_modern.presentation.base.BaseViewModel
import kz.veter420.android_modern.presentation.base.Reducer


class ProductDetailViewModel(
	private val getProductByIdUseCase: GetProductByIdUseCase
) : BaseViewModel<ProductDetailContract.State, ProductDetailContract.Event>() {

	private val reducer = ReducerImpl(ProductDetailContract.State())

	override val state: StateFlow<ProductDetailContract.State> get() = reducer.state

	fun updateProducts(
		id: Int,
		isRefreshing: Boolean = false
	) {
		if (isRefreshing) {
			reducer.sendEvent(ProductDetailContract.Event.Refresh)
		}
		reducer.sendEvent(ProductDetailContract.Event.GetProduct(id))
	}


	private inner class ReducerImpl(
		initial: ProductDetailContract.State
	) : Reducer<ProductDetailContract.State, ProductDetailContract.Event>(initial) {
		override fun reduce(oldState: ProductDetailContract.State, event: ProductDetailContract.Event) {
			when (event) {
				ProductDetailContract.Event.Refresh -> setState(
					oldState.copy(
						refreshing = true,
						error = null
					)
				)

				is ProductDetailContract.Event.GetProduct -> {
					getProductByIdUseCase.invoke(event.id).onEach { result ->
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
