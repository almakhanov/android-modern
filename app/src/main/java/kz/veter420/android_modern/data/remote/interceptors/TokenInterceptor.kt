package kz.veter420.android_modern.data.remote.interceptors

import kz.veter420.android_modern.data.TokenHolder
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException


class TokenInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val requestBuilder = original.newBuilder()
            .addHeader("Authorization", TokenHolder.TOKEN)

        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}
