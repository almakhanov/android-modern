package kz.veter420.android_modern.presentation.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.statusBarsHeight
import kz.veter420.android_modern.presentation.ui.theme.Colors


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheetLayout(
    bottomSheetState: ModalBottomSheetState,
    bottomSheetBackgroundColor: Color,
    modifier: Modifier = Modifier,
    sheetContent: @Composable () -> Unit,
    backgroundContent: @Composable () -> Unit
) {
    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetBackgroundColor = Color.Transparent,
        scrimColor = Colors.PrimaryMain.copy(alpha = 0.7f),
        sheetElevation = 0.dp,
        sheetContent = {
            Spacer(modifier = Modifier.statusBarsHeight())
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                    .background(
                        color = bottomSheetBackgroundColor
                    )
            ) {
                sheetContent()
                Box(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .size(40.dp, 4.dp)
                        .clip(RoundedCornerShape(corner = CornerSize(19.dp)))
                        .background(Colors.BackgroundSecondaryDark)
                        .align(Alignment.TopCenter)
                )
            }
        },
        content = {
            backgroundContent()
        },
        modifier = modifier
    )
}

const val ThreeHundred = 300

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun rememberModalBottomSheetState(
    initialValue: ModalBottomSheetValue,
    supportedModalBottomSheetValues: List<ModalBottomSheetValue> = listOf(ModalBottomSheetValue.Hidden, ModalBottomSheetValue.Expanded)
): ModalBottomSheetState {
    return rememberModalBottomSheetState(
        initialValue = initialValue,
        animationSpec = tween(ThreeHundred, easing = LinearEasing),
        skipHalfExpanded = true
    )
}