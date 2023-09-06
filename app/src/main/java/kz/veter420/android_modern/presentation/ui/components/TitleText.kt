package kz.veter420.android_modern.presentation.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp


@Composable
fun TitleText(
    text: String,
    modifier: Modifier = Modifier,
    alignment: Alignment = Alignment.CenterStart
) {
    Box(modifier = modifier.fillMaxWidth()) {
        Text(
            text = text,
            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight(600)),
            modifier = Modifier.align(alignment)
        )
    }
}