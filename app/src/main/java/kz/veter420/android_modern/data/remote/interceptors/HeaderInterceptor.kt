package kz.veter420.android_modern.data.remote.interceptors

import android.os.Build
import kz.veter420.android_modern.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException


class HeaderInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val requestBuilder = original.newBuilder()
            .addHeader("Content-Type", "application/json")
            .addHeader("Device-Type", "Android")
            .addHeader("User-Agent", "Modern/${BuildConfig.VERSION_NAME} (${BuildConfig.APPLICATION_ID}; " +
                    "build:${BuildConfig.VERSION_CODE}; " +
                    "Android SDK ${Build.VERSION.SDK_INT}) " +
                    "okhttp/${okhttp3.OkHttp.VERSION} " +
                    "${Build.MANUFACTURER} ${Build.MODEL}")

        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}
