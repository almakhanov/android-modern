package kz.veter420.android_modern.domain.use_case

import kotlinx.coroutines.flow.Flow
import kz.veter420.android_modern.data.dto.base.AsyncResult
import kz.veter420.android_modern.data.dto.response.ProductListDto
import kz.veter420.android_modern.domain.repository.ProductRepository


class GetProductsUseCase(private val repository: ProductRepository) {
	operator fun invoke(): Flow<AsyncResult<ProductListDto>> {
		return repository.getProducts()
	}
}
