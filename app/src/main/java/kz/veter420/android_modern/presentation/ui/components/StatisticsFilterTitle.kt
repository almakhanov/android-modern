package kz.veter420.android_modern.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kz.veter420.android_modern.R
import kz.veter420.android_modern.presentation.ui.theme.Colors
import kz.veter420.android_modern.utils.debounceClickable


@Composable
fun FilterButton(
    text: String,
    alignment: Alignment = Alignment.Center,
    fontSize: Int = 14,
    fontWeight: Int = 500,
    onClick: (() -> Unit)? = null
) {
    Box(
        contentAlignment = alignment,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .wrapContentSize()
                .debounceClickable {
                    onClick?.invoke()
                }
        ) {
            Text(
                text = text,
                style = TextStyle(
                    fontSize = fontSize.sp,
                    fontWeight = FontWeight(fontWeight),
                    color = Colors.TextPrimary
                ),
                modifier = Modifier.alpha(0.5f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_arrow_down),
                contentDescription = null,
                modifier = Modifier
                    .requiredSize(24.dp, 24.dp)
            )
        }
    }
}