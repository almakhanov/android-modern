package kz.veter420.android_modern.data.dto.base


sealed class AsyncResult<out T : Any?> {
    class Success<out T : Any?>(val data: T? = null) : AsyncResult<T>()
    class Error(val error: String, val code: Int = 0) : AsyncResult<Nothing>()
}

