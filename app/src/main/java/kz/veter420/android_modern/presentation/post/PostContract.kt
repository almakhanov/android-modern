package kz.veter420.android_modern.presentation.post

import kz.veter420.android_modern.data.dto.response.PostListDto
import kz.veter420.android_modern.presentation.alert.AlertData
import kz.veter420.android_modern.presentation.base.UiEvent
import kz.veter420.android_modern.presentation.base.UiState


interface PostContract {
    sealed class Event : UiEvent {
        object Refresh: Event()
        object GetPosts: Event()
    }
    data class State(
        val data: PostListDto? = null,
        val loading: Boolean = true,
        val error: AlertData? = null,
        val refreshing: Boolean = false
    ) : UiState
}
