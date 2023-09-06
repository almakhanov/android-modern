package kz.veter420.android_modern.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kz.veter420.android_modern.presentation.ui.theme.Colors


@Composable
fun OutlinedButton(
    text: String,
    iconRes: Int? = null,
    onClick: () -> Unit
) {

    Box(
        modifier = Modifier
            .height(44.dp)
            .fillMaxWidth()
            .fillMaxWidth(fraction = 0.68f)
            .border(
                width = 1.dp,
                brush = Colors.Gradient,
                shape = RoundedCornerShape(10.dp)
            )
            // To make the ripple round
            .clip(shape = RoundedCornerShape(10.dp))
            .clickable {
                onClick.invoke()
            },
        contentAlignment = Alignment.Center
    ) {
        Row(modifier = Modifier.wrapContentSize()) {
            iconRes?.let {
                Image(
                    painter = painterResource(id = it),
                    contentDescription = null,
                    modifier = Modifier.requiredSize(24.dp),
                    colorFilter = ColorFilter.tint(color = Colors.PrimaryMain)
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
            Text(
                text = text,
                fontSize = 16.sp,
                color = Colors.PrimaryMain,
                fontWeight = FontWeight(600)
            )
        }
    }
}