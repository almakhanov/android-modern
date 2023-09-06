package kz.veter420.android_modern.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import kz.veter420.android_modern.presentation.ui.theme.Colors
import kz.veter420.android_modern.presentation.ui.theme.MainTheme
import kz.veter420.android_modern.utils.debounceClickable
import kz.veter420.android_modern.utils.setGradient

@Composable
fun LoadingView(alpha: Float = 1f) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .alpha(alpha)
            .background(color = Colors.BackgroundPrimaryDark)
            .zIndex(1f)
            .debounceClickable {  }
    ) {
        CircularProgressIndicator(
            color = Color.White,
            strokeWidth = 4.dp,
            modifier = Modifier
                .padding(8.dp)
                .size(48.dp)
                .setGradient(Colors.Gradient)
        )
    }
}

@Preview
@Composable
private fun LoadingViewPrev() {
    MainTheme {
        LoadingView()
    }
}