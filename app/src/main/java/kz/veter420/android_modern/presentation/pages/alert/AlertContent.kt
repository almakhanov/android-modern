package kz.veter420.android_modern.presentation.pages.alert

import android.annotation.SuppressLint
import android.view.Gravity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogWindowProvider
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kz.veter420.android_modern.R
import kz.veter420.android_modern.presentation.ui.theme.Colors

private const val DELAY = 2000L

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun AlertContent(
	alert: MutableState<AlertData?>,
	onDismiss: (() -> Unit)? = null
) {
    val coroutineScope = rememberCoroutineScope()
    if (alert.value != null) {
        coroutineScope.launch {
            delay(DELAY)
            onDismiss?.invoke()
            alert.value = null
        }
        Dialog(onDismissRequest = {
            onDismiss?.invoke()
            alert.value = null
        }) {
            val dialogWindowProvider = LocalView.current.parent as? DialogWindowProvider
            dialogWindowProvider?.window?.setDimAmount(0f)
            dialogWindowProvider?.window?.setGravity(Gravity.TOP)
            Surface(
                color = Colors.BackgroundSecondaryDark,
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .wrapContentHeight()
            ) {
                Row(modifier = Modifier
                    .padding(all = 12.dp)
                    .fillMaxWidth()) {
                    val resId = when (alert.value?.type) {
                        AlertData.Type.Error -> R.drawable.ic_error
                        AlertData.Type.Success -> R.drawable.ic_success
                        else -> R.drawable.ic_error
                    }
                    Image(
                        painter = painterResource(id = resId),
                        contentDescription = null,
                        modifier = Modifier
                            .requiredSize(24.dp, 24.dp)
                    )
                    Text(
                        text = alert.value?.message.orEmpty(),
                        style = TextStyle(
                            color = Colors.TextPrimary,
                            fontSize = 14.sp,
                            fontWeight = FontWeight(500)
                        ),
                        modifier = Modifier
                            .padding(12.dp, 0.dp, 0.dp, 0.dp)
                            .fillMaxWidth()
                    )
                }
            }

        }
    }
}