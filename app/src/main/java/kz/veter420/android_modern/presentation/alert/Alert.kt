package kz.veter420.android_modern.presentation.alert


data class AlertData(
    val message: String,
    val type: Type = Type.Error
) {
    enum class Type {
        Error,
        Success
    }
}
