package kz.veter420.android_modern.data.dto.base

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException

data class ErrorResponse(
    val error: String?,
    val exception: String?,
    val message: String?,
    val description: String?,
) {

    fun print(default: String): String {
        return error ?: description ?: message ?: default
    }

    companion object {
        /**
         * Парсинг ответа сервера вручную в объект [ErrorResponse]
         *
         * Этот метод должен оставаться приватным
         * (ничего страшного, что каждый раз создаётся новый объект)
         */
        @Throws(JsonSyntaxException::class)
        private fun from(response: String): ErrorResponse {
            return Gson().fromJson(response, ErrorResponse::class.java)
        }

        fun print(response: String, default: String) = try {
            from(response).print(default)
        } catch (e: Exception) {
            default
        }

        /**
         * Проверка условия для ответа сервера
         *
         * например, мы хотим выяснить, есть ли в ответе поле [error_description]
         * и равно ли оно "User locked"
         */
        fun checkCondition(response: String, condition: ErrorResponse.() -> Boolean) = try {
            from(response).condition()
        } catch (e: Exception) {
            false
        }
    }
}