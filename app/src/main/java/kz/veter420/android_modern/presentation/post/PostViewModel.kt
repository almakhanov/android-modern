package kz.veter420.android_modern.presentation.post

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kz.veter420.android_modern.data.dto.base.AsyncResult
import kz.veter420.android_modern.presentation.base.BaseViewModel
import kz.veter420.android_modern.presentation.base.Reducer
import kz.veter420.android_modern.domain.use_case.GetPostsUseCase
import kz.veter420.android_modern.presentation.alert.AlertData


class PostViewModel(
	private val getPostsUseCase: GetPostsUseCase
) : BaseViewModel<PostContract.State, PostContract.Event>() {

	private val reducer = ReducerImpl(PostContract.State())

	override val state: StateFlow<PostContract.State> get() = reducer.state

	init {
		updatePosts()
	}

	fun updatePosts(
		isRefreshing: Boolean = false
	) {
		if (isRefreshing) {
			reducer.sendEvent(PostContract.Event.Refresh)
		}
		reducer.sendEvent(PostContract.Event.GetPosts)
	}


	private inner class ReducerImpl(
		initial: PostContract.State
	) : Reducer<PostContract.State, PostContract.Event>(initial) {
		override fun reduce(oldState: PostContract.State, event: PostContract.Event) {
			when (event) {
				PostContract.Event.Refresh -> setState(
					oldState.copy(
						refreshing = true,
						error = null
					)
				)

				PostContract.Event.GetPosts -> {
					getPostsUseCase.invoke().onEach { result ->
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
