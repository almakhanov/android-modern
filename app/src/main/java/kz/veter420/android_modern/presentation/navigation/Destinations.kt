package kz.veter420.android_modern.presentation.navigation


sealed class Destinations(val route: String) {
	object Post: Destinations("post}")
	object Profile: Destinations("profile")
	object Products: Destinations("products")
	data class ProductDetailPage(val id: String = "id"): Destinations("products/{id}")
}