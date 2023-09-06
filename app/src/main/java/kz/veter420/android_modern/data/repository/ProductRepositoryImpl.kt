package kz.veter420.android_modern.data.repository

import kotlinx.coroutines.flow.Flow
import kz.veter420.android_modern.data.api.OpenApi
import kz.veter420.android_modern.data.dto.base.AsyncResult
import kz.veter420.android_modern.data.dto.response.PostListDto
import kz.veter420.android_modern.data.dto.response.ProductDto
import kz.veter420.android_modern.data.dto.response.ProductListDto
import kz.veter420.android_modern.data.remote.BaseRepository
import kz.veter420.android_modern.domain.repository.PostRepository
import kz.veter420.android_modern.domain.repository.ProductRepository


class ProductRepositoryImpl(private val api: OpenApi) : BaseRepository(), ProductRepository {

	override fun getProducts(): Flow<AsyncResult<ProductListDto>> {
		return runInFlow(api.getProducts())
	}

	override fun getProductById(id: Int): Flow<AsyncResult<ProductDto>> {
		return runInFlow(api.getProductById(id))
	}
}
