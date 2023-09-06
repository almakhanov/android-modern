package kz.veter420.android_modern.presentation.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import kz.veter420.android_modern.presentation.ui.theme.Colors

@Composable
fun DescriptionText(
    text: String,
    fontSize: Int = 16,
    fontWeight: Int = 400,
    alignment: Alignment = Alignment.CenterStart
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = text,
            style = TextStyle(
                fontSize = fontSize.sp,
                fontWeight = FontWeight(fontWeight),
                color = Colors.TextPrimary
            ),
            modifier = Modifier
                .align(alignment)
                .alpha(0.5f)
        )
    }
}