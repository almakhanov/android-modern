package kz.veter420.android_modern.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kz.veter420.android_modern.R
import kz.veter420.android_modern.presentation.ui.theme.Colors
import kz.veter420.android_modern.utils.debounceClickable


@Composable
fun TextToolbar(
    modifier: Modifier = Modifier,
    text: String,
    showBackButton: Boolean,
    actionIconRes: Int? = null,
    onClickBack: (() -> Unit)? = null,
    onClickAction: (() -> Unit)? = null
) {
    Box(
        modifier = modifier
            .height(56.dp)
            .background(color = Colors.BackgroundPrimaryDark)
    ) {
        if (showBackButton) {
            Box(modifier = Modifier
                .fillMaxSize()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_arrow_back),
                    contentDescription = null,
                    modifier = Modifier
                        .requiredSize(24.dp, 24.dp)
                        .align(Alignment.CenterStart)
                        .debounceClickable {
                            onClickBack?.invoke()
                        }
                )
            }
        }
        Box(modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.Center)) {
            Text(
                text = text,
                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight(600)),
                modifier = Modifier.align(Alignment.Center)
            )
        }
        if (actionIconRes != null) {
            Box(modifier = Modifier
                .fillMaxSize()
            ) {
                Image(
                    painter = painterResource(id = actionIconRes),
                    contentDescription = null,
                    modifier = Modifier
                        .requiredSize(24.dp, 24.dp)
                        .align(Alignment.CenterEnd)
                        .debounceClickable {
                            onClickAction?.invoke()
                        }
                )
            }
        }
    }
}