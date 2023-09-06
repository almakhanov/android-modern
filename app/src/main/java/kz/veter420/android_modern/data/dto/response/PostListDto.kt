package kz.veter420.android_modern.data.dto.response


data class PostListDto(
    val posts: List<PostDto>,
    val limit: Int,
    val skip: Int,
    val total: Int
)
