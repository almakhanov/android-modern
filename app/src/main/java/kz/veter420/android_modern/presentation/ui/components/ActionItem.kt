package kz.veter420.android_modern.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kz.veter420.android_modern.R
import kz.veter420.android_modern.presentation.ui.theme.Colors


@Composable
fun ActionItem(
    text: String,
    iconRes: Int,
    iconColor: Color = Colors.ElementType1,
    onClick: () -> Unit
) {

    Box(modifier = Modifier
        .fillMaxSize()
        .background(
            color = Colors.BackgroundSecondaryDark,
            shape = RoundedCornerShape(10.dp)
        )
        .clip(shape = RoundedCornerShape(10.dp))
        .clickable {
        onClick.invoke()
    }) {
        Row(modifier = Modifier.padding(12.dp)) {
            Image(
                painter = painterResource(id = iconRes),
                contentDescription = null,
                modifier = Modifier.requiredSize(24.dp),
                colorFilter = ColorFilter.tint(color = iconColor)
            )

            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = text,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight(600),
                    color = Colors.TextPrimary
                )
            )
            Box(modifier = Modifier.fillMaxSize()) {
                Image(
                    painter = painterResource(id = R.drawable.ic_arrow_right),
                    contentDescription = null,
                    modifier = Modifier
                        .requiredSize(24.dp, 24.dp)
                        .align(Alignment.CenterEnd)
                )
            }
        }
    }
}
