package kz.veter420.android_modern.presentation.post

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import kz.veter420.android_modern.R
import kz.veter420.android_modern.presentation.alert.AlertContent
import kz.veter420.android_modern.presentation.ui.components.LoadingView
import kz.veter420.android_modern.presentation.ui.components.PostViewItem
import kz.veter420.android_modern.presentation.ui.components.TextToolbar
import kz.veter420.android_modern.presentation.ui.theme.Colors
import org.koin.androidx.compose.koinViewModel


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PostScreen(
	navController: NavController,
	mViewModel: PostViewModel = koinViewModel()
) {
	Column {
		PostScreenContent(
			navController = navController,
			mViewModel = mViewModel
		)
	}
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PostScreenContent(navController: NavController, mViewModel: PostViewModel) {
	val state by mViewModel.state.collectAsState()
	val idleStateLoading = remember(state) { mutableStateOf(state.loading) }
	val errorState = remember(state) { mutableStateOf(state.error) }

	val refreshState = rememberPullRefreshState(refreshing = state.refreshing, onRefresh = {
		mViewModel.updatePosts(true)
	})

	AlertContent(errorState)

	Column(
		modifier = Modifier
			.fillMaxSize()
			.padding(0.dp, 0.dp)
	) {
		TextToolbar(
			text = stringResource(id = R.string.posts),
			showBackButton = false,
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
				Modifier.align(Alignment.TopCenter),
				backgroundColor = Colors.TextPrimary,
				contentColor = Colors.PrimaryMain
			)
			Column(
				modifier = Modifier
					.fillMaxWidth()
					.padding()
			) {
				val list = state.data?.posts
				if (!list.isNullOrEmpty()) {
					LazyColumn(
						modifier = Modifier
							.fillMaxWidth()
							.padding(0.dp, 0.dp)
							.zIndex(0.5f)
					) {
						this.itemsIndexed(
							items = list,
						) { index, item ->
							PostViewItem(
								item = item,
								onClick = {
								}
							)
						}
						this.item {
							Spacer(modifier = Modifier.height(100.dp))
						}
					}
				}
			}
		}
	}

}