package kz.veter420.android_modern.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import kz.veter420.android_modern.R
import kz.veter420.android_modern.data.dto.response.ProductDto
import kz.veter420.android_modern.presentation.ui.theme.Colors
import kz.veter420.android_modern.utils.debounceClickable


@Composable
fun ProductViewItem(
	modifier: Modifier = Modifier,
	item: ProductDto,
	onClick: (ProductDto) -> Unit
) {
	Column(
		modifier = modifier
			.fillMaxWidth()
			.padding(16.dp)
			.debounceClickable { onClick.invoke(item) }
	) {
		if (!item.images.isNullOrEmpty()) {
			val painter = rememberAsyncImagePainter(
				model = ImageRequest.Builder(LocalContext.current)
					.data(item.images[0])
					.size(Size.ORIGINAL)
					.build()
			)
			if (painter.state is AsyncImagePainter.State.Success) {
				Box(
					modifier = Modifier
						.height(184.dp)
						.clip(RoundedCornerShape(8.dp))
						.fillMaxWidth()
				) {
					Image(
						painter = painter,
						contentDescription = null,
						contentScale = ContentScale.Crop,
						modifier = Modifier
							.height(184.dp)
							.clip(RoundedCornerShape(8.dp))
							.fillMaxWidth()
					)
				}
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
			maxLines = 2,
			overflow = TextOverflow.Ellipsis,
			modifier = Modifier
				.fillMaxWidth()
				.alpha(0.5f)
				.padding(top = 8.dp)
		)
		Text(
			text = "Read more...",
			style = TextStyle(
				fontSize = 14.sp,
				fontWeight = FontWeight(500),
				color = Colors.TextPrimary,
				textDecoration = TextDecoration.Underline
			),
			modifier = Modifier
				.alpha(0.5f)
				.padding(top = 8.dp)

		)
	}
	Divider(
		modifier = Modifier
			.fillMaxWidth()
			.alpha(0.1f),
		color = Colors.TextPrimary,
		thickness = 1.dp
	)
}
