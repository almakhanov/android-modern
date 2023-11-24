package kz.veter420.android_modern.presentation.navigation


sealed class Destinations(val route: String) {
	data object Post : Destinations("post")
	data object Calculator : Destinations("calculator")
	data object Products : Destinations("products")
	data class ProductDetailPage(val id: String = "id") : Destinations("products/{id}")
}