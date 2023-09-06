package kz.veter420.android_modern.presentation.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kz.veter420.android_modern.presentation.ui.theme.Colors


@Composable
fun ActionText(
    text: String,
    color: Color = Colors.TextPrimary,
    onClick: (() -> Unit)? = null
) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .clickable { onClick?.invoke() }) {
        Text(
            text = text,
            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight(600), color = color),
            modifier = Modifier.align(Alignment.Center).padding(10.dp)
        )
    }
}