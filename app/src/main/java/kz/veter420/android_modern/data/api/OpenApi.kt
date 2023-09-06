package kz.veter420.android_modern.data.api

import kotlinx.coroutines.Deferred
import kz.veter420.android_modern.data.dto.response.PostListDto
import kz.veter420.android_modern.data.dto.response.ProductDto
import kz.veter420.android_modern.data.dto.response.ProductListDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface OpenApi {

    @GET("posts")
    fun getPosts(): Deferred<Response<PostListDto>>

    @GET("products")
    fun getProducts(): Deferred<Response<ProductListDto>>

    @GET("products/{id}")
    fun getProductById(@Path("id") id: Int): Deferred<Response<ProductDto>>

}
