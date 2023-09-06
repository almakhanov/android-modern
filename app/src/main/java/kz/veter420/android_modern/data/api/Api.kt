package kz.veter420.android_modern.data.api

import kotlinx.coroutines.Deferred
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query


interface Api {

//    @GET("user/get-user-profile")
//    fun getProfile(): Deferred<Response<Profile>>
//
//    @GET("branch/dashboard")
//    fun getBranchDashboard(
//        @Query("id") branchId: Int,
//        @Query("month") month: Int,
//        @Query("year") year: Int
//    ): Deferred<Response<Dashboard>>
//
//    @GET("sales-manager/dashboard")
//    fun getManagerDashboard(
//        @Query("user_id") userId: Int,
//        @Query("month") month: Int,
//        @Query("year") year: Int
//    ): Deferred<Response<Dashboard>>
//
//    @GET("sales-manager/year-statistic")
//    fun getManagerSaleTable(
//        @Query("user_id") userId: Int,
//        @Query("year") year: Int
//    ): Deferred<Response<BigTableDto>>
//
//    @GET("branch/year-statistic")
//    fun getBranchSaleTable(
//        @Query("branch_id") branchId: Int,
//        @Query("year") year: Int
//    ): Deferred<Response<BigTableDto>>
//
//    @GET("rating/managers")
//    fun getManagersRating(
//        @Query("branch_id") branchId: Int,
//        @Query("month") month: Int,
//        @Query("year") year: Int,
//        @Query("page") page: Int,
//        @Query("limit") limit: Int,
//    ): Deferred<Response<GridResponse<ManagerRating>>>
//
//    @GET("rating/branches")
//    fun getBranchesRating(
//        @Query("month") month: Int,
//        @Query("year") year: Int,
//        @Query("page") page: Int,
//        @Query("limit") limit: Int,
//    ): Deferred<Response<GridResponse<BranchRating>>>
//
//    @GET("sales-manager/sale/list")
//    fun getSaleList(
//        @Query("user_id") userId: Int,
//        @Query("month") month: Int,
//        @Query("year") year: Int,
//        @Query("page") page: Int,
//        @Query("limit") limit: Int,
//    ): Deferred<Response<GridResponse<SaleItem>>>
//
//    @GET("admin/sale-type/list")
//    fun getSaleTypes(): Deferred<Response<GridResponse<SaleType>>>
//
//    @POST("sales-manager/sale/new")
//    fun createSale(@Body body: SaleItemRequest): Deferred<Response<SaleItem>>
//
//    @POST("sales/edit")
//    fun editSale(@Body body: SaleItemRequest): Deferred<Response<SaleItem>>
//
//    @HTTP(method = "DELETE", path = "sales/delete", hasBody = true)
//    fun deleteSale(@Body body: SaleDeleteRequest): Deferred<Response<Unit>>
//
//    @GET("branch/sales-managers")
//    fun getBranchSalesManagers(
//        @Query("branch_id") branchId: Int,
//        @Query("month") month: Int,
//        @Query("year") year: Int
//    ): Deferred<Response<GridResponse<ShortSaleManager>>>
//
//    @GET("director/sales-manager/goal")
//    fun getSalesManagerGoal(
//        @Query("branch_id") branchId: Int,
//        @Query("user_id") userId: Int,
//        @Query("month") month: Int,
//        @Query("year") year: Int,
//        @Query("sale_type_id") saleTypeId: Int
//    ): Deferred<Response<SalesManagerGoal>>
//
//    @POST("director/sales-manager/goal")
//    fun editManagerGoal(@Body body: SetGoalRequest): Deferred<Response<SalesManagerGoal>>
//
//    @GET("news")
//    fun getNews(
//        @Query("page") page: Int,
//        @Query("limit") limit: Int
//    ): Deferred<Response<GridResponse<News>>>
//
//    @POST("image/avatar/upload")
//    @Multipart
//    fun uploadAvatar(@Part file: MultipartBody.Part?): Deferred<Response<ImageUrlResponse>>
//
//    @POST("user/avatar")
//    fun updateAvatar(@Body body: ImageUrlResponse): Deferred<Response<Unit>>
//
//    @DELETE("user/avatar")
//    fun deleteAvatar(): Deferred<Response<Unit>>
//
//    @POST("news/like-toggle")
//    fun setNewsLike(@Body body: ToggleNewsFavoriteRequest): Deferred<Response<Unit>>
//
//    @HTTP(method = "DELETE", path = "news/delete", hasBody = true)
//    fun deleteNews(@Body body: DeleteNewsRequest): Deferred<Response<Unit>>
//
//    @POST("news/new")
//    fun createNews(@Body body: AddNewsRequest): Deferred<Response<Unit>>
//
//    @POST("image/news/upload")
//    @Multipart
//    fun uploadNewsImage(@Part file: MultipartBody.Part?): Deferred<Response<ImageUrlResponse>>
//
//    @GET("news/comments")
//    fun getComments(
//        @Query("page") page: Int,
//        @Query("limit") limit: Int,
//        @Query("news_id") news_id: Int,
//    ): Deferred<Response<GridResponse<Comment>>>
//
//    @POST("news/comments/new")
//    fun createComment(@Body body: AddCommentRequest): Deferred<Response<Unit>>
//
//    @HTTP(method = "DELETE", path = "news/comments/delete", hasBody = true)
//    fun deleteComment(@Body body: DeleteCommentRequest): Deferred<Response<Unit>>
}
