package kz.veter420.android_modern.data.dto.response


data class PostDto(
    val body: String,
    val id: Int,
    val reactions: Int,
    val tags: List<String>,
    val title: String,
    val userId: Int
)
