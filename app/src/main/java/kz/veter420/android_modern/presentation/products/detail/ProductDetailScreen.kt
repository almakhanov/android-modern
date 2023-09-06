package kz.veter420.android_modern.presentation.products.detail

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kz.veter420.android_modern.R
import kz.veter420.android_modern.presentation.alert.AlertContent
import kz.veter420.android_modern.presentation.ui.components.DotsIndicator
import kz.veter420.android_modern.presentation.ui.components.LoadingView
import kz.veter420.android_modern.presentation.ui.components.TextToolbar
import kz.veter420.android_modern.presentation.ui.theme.Colors
import org.koin.androidx.compose.koinViewModel


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ProductDetailScreen(
	navController: NavController,
	productId: Int,
	mViewModel: ProductDetailViewModel = koinViewModel()
) {
	Column {
		ProductDetailScreenContent(
			navController = navController,
			productId,
			mViewModel = mViewModel
		)
	}
}


@OptIn(ExperimentalMaterialApi::class, ExperimentalPagerApi::class)
@Composable
fun ProductDetailScreenContent(navController: NavController, productId: Int, mViewModel: ProductDetailViewModel) {
	val state by mViewModel.state.collectAsState()
	val idleStateLoading = remember(state) { mutableStateOf(state.loading) }
	val errorState = remember(state) { mutableStateOf(state.error) }

	val refreshState = rememberPullRefreshState(refreshing = state.refreshing, onRefresh = {
		mViewModel.updateProducts(productId, true)
	})

	LaunchedEffect(Unit) {
		mViewModel.updateProducts(productId)
	}

	AlertContent(errorState)

	Column(
		modifier = Modifier
			.fillMaxSize()
			.padding(16.dp, 0.dp)
	) {
		TextToolbar(
			text = stringResource(id = R.string.product),
			showBackButton = true,
			onClickBack = {
				navController.popBackStack()
			}
		)
		Box(
			modifier = Modifier
				.fillMaxSize()
				.pullRefresh(refreshState)
		) {
			if (idleStateLoading.value) {
				LoadingView(0.7f)
			}
			PullRefreshIndicator(
				state.refreshing,
				refreshState,
				Modifier
					.align(Alignment.TopCenter)
					.zIndex(1f),
				backgroundColor = Colors.TextPrimary,
				contentColor = Colors.PrimaryMain
			)
			Column(
				modifier = Modifier
					.fillMaxWidth()
					.padding()
			) {
				val item = state.data ?: return
				if (!item.images.isNullOrEmpty()) {
					val images = item.images
					val pagerState = rememberPagerState(pageCount = images.size)
					HorizontalPager(
						state = pagerState
					) { page ->
						Box(contentAlignment = Alignment.BottomCenter) {
							Image(
								painter = rememberAsyncImagePainter(images[page]),
								contentDescription = "",
								Modifier
									.clip(RoundedCornerShape(8.dp))
									.height(256.dp)
									.fillMaxWidth(),
								contentScale = ContentScale.FillWidth
							)
						}
					}
					if (images.size > 1) {
						Spacer(modifier = Modifier.padding(8.dp))
						DotsIndicator(
							totalDots = images.size,
							selectedIndex = pagerState.currentPage
						)
					}
				}

				Text(
					text = item.title,
					style = TextStyle(
						fontSize = 16.sp,
						fontWeight = FontWeight(600),
						color = Colors.TextPrimary
					),
					modifier = Modifier
						.fillMaxWidth()
						.padding(top = 16.dp)
				)
				Text(
					text = item.description,
					style = TextStyle(
						fontSize = 14.sp,
						fontWeight = FontWeight(500),
						color = Colors.TextPrimary
					),
					modifier = Modifier
						.fillMaxWidth()
						.alpha(0.5f)
						.padding(top = 8.dp)
				)

				Divider(
					modifier = Modifier
						.fillMaxWidth()
						.alpha(0.1f)
						.padding(top = 16.dp),
					color = Colors.TextPrimary,
					thickness = 1.dp
				)
			}
		}
	}

}

