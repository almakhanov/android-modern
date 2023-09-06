package kz.veter420.android_modern.data.dto.base


data class GridResponse<T>(
    val result: List<T>,
    val count: Int,
    val has_next: Boolean
)
