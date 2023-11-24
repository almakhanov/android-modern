package kz.veter420.android_modern.presentation.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kz.veter420.android_modern.data.dto.response.PostDto
import kz.veter420.android_modern.presentation.ui.theme.Colors
import kz.veter420.android_modern.presentation.utils.debounceClickable

@Composable
fun PostViewItem(
	modifier: Modifier = Modifier,
	item: PostDto,
	onClick: (PostDto) -> Unit
) {
	Column(
		modifier = modifier
			.fillMaxWidth()
			.debounceClickable(ripple = true) { onClick.invoke(item) }
			.padding(16.dp)
	) {
		Text(
			text = item.title,
			style = TextStyle(
				fontSize = 16.sp,
				fontWeight = FontWeight(600),
				color = Colors.TextPrimary
			),
			modifier = Modifier.fillMaxWidth()
		)
		Text(
			text = item.body,
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
	}
	Divider(
		modifier = Modifier
			.fillMaxWidth()
			.alpha(0.1f),
		color = Colors.TextPrimary,
		thickness = 1.dp
	)
}