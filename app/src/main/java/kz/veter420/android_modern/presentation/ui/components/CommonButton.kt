package kz.veter420.android_modern.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kz.veter420.android_modern.presentation.ui.theme.Colors


@Composable
fun CommonButton(
    modifier: Modifier = Modifier,
    text: String,
    iconRes: Int? = null,
    onClick: () -> Unit,
    enabled: Boolean = true,
    loading: Boolean = false,
) {

    Button(
        modifier = modifier
            .height(44.dp)
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
        contentPadding = PaddingValues(),
        onClick = {
            if (enabled) onClick()
        },
    ) {
        Box(
            modifier = Modifier
                .background(
                    brush = Colors.Gradient,
                    shape = RoundedCornerShape(10.dp),
                    alpha = if (enabled) 1F else 0.5F
                )
                .height(44.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center,
        ) {
            if (loading) {
                CircularProgressIndicator(
                    color = Color.White,
                    strokeWidth = 2.dp,
                    modifier = Modifier
                        .padding(8.dp)
                        .size(24.dp)
                )
            } else {
                Row(modifier = Modifier.wrapContentSize()) {
                    iconRes?.let {
                        Image(
                            painter = painterResource(id = it),
                            contentDescription = null,
                            modifier = Modifier.requiredSize(24.dp),
                            colorFilter = ColorFilter.tint(color = Colors.TextPrimary)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                    Text(
                        text = text,
                        fontSize = 16.sp,
                        color = Colors.TextPrimary,
                        fontWeight = FontWeight(600)
                    )
                }
            }
        }
    }
}