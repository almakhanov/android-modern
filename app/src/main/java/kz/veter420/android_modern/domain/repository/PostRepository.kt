package kz.veter420.android_modern.domain.repository

import kotlinx.coroutines.flow.Flow
import kz.veter420.android_modern.data.dto.base.AsyncResult
import kz.veter420.android_modern.data.dto.response.PostListDto


interface PostRepository {
	fun getAllPosts(): Flow<AsyncResult<PostListDto>>
}
