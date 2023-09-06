package kz.veter420.android_modern.data.remote

import android.util.Log
import com.google.gson.JsonParseException
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.flow.flow
import kz.veter420.android_modern.data.dto.base.AsyncResult
import kz.veter420.android_modern.data.dto.base.ErrorResponse
import retrofit2.HttpException
import retrofit2.Response
import java.net.ConnectException
import java.net.HttpURLConnection.HTTP_BAD_REQUEST
import java.net.HttpURLConnection.HTTP_INTERNAL_ERROR
import java.net.HttpURLConnection.HTTP_UNAUTHORIZED
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLHandshakeException


abstract class BaseRepository {

    companion object {
        private val HTTP_CODE_TOKEN_EXPIRED = 420
        private val HTTP_CODE_ACCOUNT_BLOCKED = 419
        private val SERVER_ERROR = "Внутренняя ошибка сервера"
    }

    fun <T> runInFlow(deferred: Deferred<Response<T>>) = flow {
        runCatching {
            emit(handleResult(deferred.await()))
        }.exceptionOrNull()?.let {
            emit(handleException<T>(it))
        }
    }


    suspend fun <T> runRequest(deferred: Deferred<Response<T>>): AsyncResult<T>? {
        return try {
            handleResult(deferred.await())
        } catch (e: Exception) {
            handleException(e)
        }
    }

    private fun <T> handleResult(result: Response<T>): AsyncResult<T> {
        return try {
            if (result.isSuccessful) {
                AsyncResult.Success(result.body())
            } else {
                throw HttpException(result)
            }
        } catch (e: Exception) {
            handleException(e)
        }
    }

    private fun <T> handleException(e: Throwable): AsyncResult<T> {
        return when (e) {
            is HttpException -> {
                when (e.code()) {
                    HTTP_BAD_REQUEST -> {
                        Log.e("$$$", "User not authorized, should exit application")
                        val errorBody = e.response()?.errorBody()?.string().orEmpty()
                        AsyncResult.Error(
                            ErrorResponse.print(errorBody, "Неправильные данные"),
                            e.code()
                        )
                    }
                    HTTP_UNAUTHORIZED -> {
                        Log.e("$$$", "User not authorized, should exit application")
                        val errorBody = e.response()?.errorBody()?.string().orEmpty()
                        AsyncResult.Error(
                            ErrorResponse.print(errorBody, "Ошибка авторизации"),
                            e.code()
                        )
                    }

                    HTTP_CODE_TOKEN_EXPIRED -> {
                        val errorBody = e.response()?.errorBody()?.string().orEmpty()
                        Log.e("$$$", "User's token has expired, should exit application")
                        AsyncResult.Error(
                            ErrorResponse.print(errorBody, "Срок действия сессии истек"),
                            e.code()
                        )
                    }

                    HTTP_CODE_ACCOUNT_BLOCKED -> {
                        val errorBody = e.response()?.errorBody()?.string().orEmpty()
                        Log.e("$$$", "User's account is blocked, should exit application")
                        AsyncResult.Error(
                            ErrorResponse.print(errorBody, "Ваш аккаунт заблокирован"),
                            e.code()
                        )
                    }

                    HTTP_INTERNAL_ERROR -> {
                        val result = e.response()?.errorBody()?.string() ?: ""
                        Log.e("$$$", "Internal server error:\n$result")
                        AsyncResult.Error(
                            ErrorResponse.print(result, SERVER_ERROR),
                            e.code()
                        )
                    }

                    else -> {
                        Log.e("$$$", "Server error ${e.code()}")
                        val errorBody = e.response()?.errorBody()?.string().orEmpty()
                        AsyncResult.Error(
                            ErrorResponse.print(errorBody, "Сервер вернул ошибку ${e.code()}"),
                            e.code()
                        )
                    }
                }
            }

            is SocketTimeoutException -> {
                Log.e("$$$", "Request timeout")
                AsyncResult.Error("Сервер не отвечает")
            }

            is SSLHandshakeException -> {
                Log.e("$$$", "SSLHandshakeException: ${e.message}")
                AsyncResult.Error("Возникли проблемы с сертификатом")
            }

            is JsonParseException -> {
                Log.e("$$$", "JsonParseException: ${e.message}")
                AsyncResult.Error("Ошибка обработки запроса")
            }

            is UnknownHostException,
            is ConnectException -> {
                Log.e("$$$", "ConnectException: ${e.message}")
                AsyncResult.Error("Проверьте подключение к интернету")
            }

            else -> {
                Log.e("$$$", "Unhandled request exception $e")
                AsyncResult.Error("Ошибка: ${e.javaClass.simpleName} ${e.localizedMessage}")
            }
        }
    }
}
