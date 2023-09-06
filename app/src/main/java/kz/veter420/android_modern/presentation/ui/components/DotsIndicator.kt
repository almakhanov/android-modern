package kz.veter420.android_modern.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import kz.veter420.android_modern.presentation.ui.theme.Colors


@Composable
fun DotsIndicator(
	totalDots: Int,
	selectedIndex: Int
) {

	LazyRow(
		modifier = Modifier
			.fillMaxWidth()
			.wrapContentHeight(),
		horizontalArrangement = Arrangement.Center,
		verticalAlignment = Alignment.CenterVertically
	) {
		items(totalDots) { index ->
			if (index == selectedIndex) {
				Box(
					modifier = Modifier
						.size(8.dp)
						.clip(CircleShape)
						.background(color = Colors.PrimaryMain)
				)
			} else {
				Box(
					modifier = Modifier
						.size(6.dp)
						.clip(CircleShape)
						.background(color = Colors.TextSecondary)
				)
			}

			if (index != totalDots - 1) {
				Spacer(modifier = Modifier.padding(horizontal = 2.dp))
			}
		}
	}
}