package kz.veter420.android_modern.domain.repository

import kotlinx.coroutines.flow.Flow
import kz.veter420.android_modern.data.dto.base.AsyncResult
import kz.veter420.android_modern.data.dto.response.ProductDto
import kz.veter420.android_modern.data.dto.response.ProductListDto


interface ProductRepository {
	fun getProducts(): Flow<AsyncResult<ProductListDto>>
	fun getProductById(id: Int): Flow<AsyncResult<ProductDto>>
}
