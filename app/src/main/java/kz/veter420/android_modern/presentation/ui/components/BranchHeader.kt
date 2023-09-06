package kz.veter420.android_modern.presentation.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kz.veter420.android_modern.presentation.ui.theme.Colors

@Composable
fun BranchHeader(
    modifier: Modifier,
    title: String,
    branch: String? = null
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.wrapContentSize()) {
            Text(
                text = title,
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight(600),
                    color = Colors.TextPrimary
                )
            )
            branch?.let {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = it,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight(500),
                        color = Colors.TextPrimary
                    ),
                    modifier = Modifier.alpha(0.5f)
                )
            }
        }
    }
}