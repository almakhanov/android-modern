package kz.veter420.android_modern.data.remote

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


inline fun <reified T> createWebService(okHttpClient: OkHttpClient, url: String): T {
    val gson = GsonBuilder()
        .setLenient()
        .create()

    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create(gson)).build()
    return retrofit.create(T::class.java)
}
