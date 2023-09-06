package kz.veter420.android_modern.data.repository

import kotlinx.coroutines.flow.Flow
import kz.veter420.android_modern.data.api.OpenApi
import kz.veter420.android_modern.data.dto.base.AsyncResult
import kz.veter420.android_modern.data.dto.response.PostListDto
import kz.veter420.android_modern.data.remote.BaseRepository
import kz.veter420.android_modern.domain.repository.PostRepository


class PostRepositoryImpl(private val api: OpenApi): BaseRepository(), PostRepository {
	override fun getAllPosts(): Flow<AsyncResult<PostListDto>> {
		return runInFlow(api.getPosts())
	}
}
