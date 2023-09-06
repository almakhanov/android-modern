package kz.veter420.android_modern.data.dto.response


data class ProductListDto(
	val products: List<ProductDto>,
	val limit: Int,
	val skip: Int,
	val total: Int
)
