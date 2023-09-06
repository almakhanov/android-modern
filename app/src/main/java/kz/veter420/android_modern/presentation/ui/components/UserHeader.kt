package kz.veter420.android_modern.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import kz.veter420.android_modern.presentation.ui.theme.Colors
import kz.veter420.android_modern.utils.nameToInitial

@Composable
fun UserHeader(
    fullName: String,
    imageUrl: String? = null,
    position: String? = null,
    branch: String? = null
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.requiredSize(64.dp)
        ) {
            if (imageUrl.isNullOrEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            color = Colors.ElementStroke,
                            shape = RoundedCornerShape(32.dp)
                        )
                ) {
                    Text(
                        text = nameToInitial(fullName),
                        style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight(500)),
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            } else {
                Image(
                    painter = rememberAsyncImagePainter(imageUrl),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .requiredSize(64.dp)
                        .clip(RoundedCornerShape(32.dp))
                        .fillMaxSize()
                )
            }
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.wrapContentSize()) {
            Text(
                text = fullName,
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight(600),
                    color = Colors.TextPrimary
                )
            )
            position?.let {
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