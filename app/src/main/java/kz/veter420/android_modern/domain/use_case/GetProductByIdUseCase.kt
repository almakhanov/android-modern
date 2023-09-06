package kz.veter420.android_modern.domain.use_case

import kotlinx.coroutines.flow.Flow
import kz.veter420.android_modern.data.dto.base.AsyncResult
import kz.veter420.android_modern.data.dto.response.ProductDto
import kz.veter420.android_modern.domain.repository.ProductRepository


class GetProductByIdUseCase(private val repository: ProductRepository) {
	operator fun invoke(id: Int): Flow<AsyncResult<ProductDto>> {
		return repository.getProductById(id)
	}
}
