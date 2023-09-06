package kz.veter420.android_modern.presentation.navigation


sealed class Destinations(val route: String) {
	object Post: Destinations("post}")
    object Product: Destinations("product")
    object Profile: Destinations("profile")
	data class ProductDetailPage(val id: String = "id"): Destinations("product_detail")
}