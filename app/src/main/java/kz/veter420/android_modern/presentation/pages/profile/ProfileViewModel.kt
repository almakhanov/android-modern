package kz.veter420.android_modern.presentation.pages.profile

import kotlinx.coroutines.flow.StateFlow
import kz.veter420.android_modern.presentation.base.BaseViewModel
import kz.veter420.android_modern.presentation.base.Reducer


class ProfileViewModel() : BaseViewModel<ProfileContract.State, ProfileContract.Event>() {

	private val reducer = ReducerImpl(ProfileContract.State())

	override val state: StateFlow<ProfileContract.State> get() = reducer.state


	private inner class ReducerImpl(
		initial: ProfileContract.State
	) : Reducer<ProfileContract.State, ProfileContract.Event>(initial) {
		override fun reduce(oldState: ProfileContract.State, event: ProfileContract.Event) {

		}
	}
}
