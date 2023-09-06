package kz.veter420.android_modern.data.remote

import kz.veter420.android_modern.BuildConfig
import kz.veter420.android_modern.data.remote.interceptors.HeaderInterceptor
import kz.veter420.android_modern.data.remote.interceptors.TokenInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.net.ssl.HostnameVerifier


object WebClientBuilder {
    private const val TIMEOUT = 120L
    private val hostnameVerifier = HostnameVerifier { _, _ -> true }
    private val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
        else HttpLoggingInterceptor.Level.NONE
    }

    fun createOpenOkHttpClient() = OkHttpClient.Builder()
        .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT, TimeUnit.SECONDS)
        .hostnameVerifier(hostnameVerifier)
        .addInterceptor(HeaderInterceptor())
        .addInterceptor(httpLoggingInterceptor)
        .build()

    fun createClosedOkHttpClient() = OkHttpClient.Builder()
        .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT, TimeUnit.SECONDS)
        .hostnameVerifier(hostnameVerifier)
        .addInterceptor(HeaderInterceptor())
        .addInterceptor(TokenInterceptor())
        .addInterceptor(httpLoggingInterceptor)
        .build()
}
