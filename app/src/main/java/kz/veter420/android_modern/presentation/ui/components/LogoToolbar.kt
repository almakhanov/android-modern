package kz.veter420.android_modern.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kz.veter420.android_modern.R


@Composable
fun LogoToolbar(showBackButton: Boolean, onClickBack: (() -> Unit)? = null) {
    if (showBackButton) {
        Box(modifier = Modifier.clickable {
            onClickBack?.invoke()
        }) {
            Image(
                painter = painterResource(id = R.drawable.ic_arrow_back),
                contentDescription = null,
                modifier = Modifier
                    .padding(16.dp)
                    .requiredSize(24.dp, 24.dp)
                    .align(Alignment.CenterStart)
            )
        }
    }
    Box(modifier = Modifier.fillMaxWidth()){
        Image(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = null,
            modifier = Modifier
                .padding(12.dp)
                .requiredSize(126.dp, 32.dp)
                .fillMaxWidth()
                .align(Alignment.Center)
        )
    }
}