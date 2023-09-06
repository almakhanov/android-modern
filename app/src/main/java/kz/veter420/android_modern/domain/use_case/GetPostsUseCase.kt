package kz.veter420.android_modern.domain.use_case

import kotlinx.coroutines.flow.Flow
import kz.veter420.android_modern.data.dto.base.AsyncResult
import kz.veter420.android_modern.data.dto.response.PostListDto
import kz.veter420.android_modern.domain.repository.PostRepository


class GetPostsUseCase(private val repository: PostRepository) {
	operator fun invoke(): Flow<AsyncResult<PostListDto>> {
		return repository.getAllPosts()
	}
}
