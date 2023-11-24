package kz.veter420.android_modern.data.dto.base


data class GridResponse<T>(
    val list: List<T>,
    val total: Int
)
