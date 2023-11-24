package kz.veter420.android_modern.presentation.utils

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.semantics.Role
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.transform


fun Modifier.debounceClickable(
    ripple: Boolean = false,
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    clickDebounceWindow: Long = 1_000L,
    onClick: () -> Unit
) = composed(
    inspectorInfo = debugInspectorInfo {
        name = "clickable"
        properties["enabled"] = enabled
        properties["onClickLabel"] = onClickLabel
        properties["role"] = role
        properties["onClick"] = onClick
    }
) {
    val debounceClickState = remember {
        MutableSharedFlow<() -> Unit>(
            extraBufferCapacity = 1,
            onBufferOverflow = BufferOverflow.DROP_OLDEST,
        )
    }

    var lastEventTime by remember { mutableLongStateOf(0L) }

    LaunchedEffect(Unit) {
        debounceClickState.transform {
            // Only emit click events if the clickDebounce
            // millis have passed since the last click event
            val now = System.currentTimeMillis()
            if (now - lastEventTime > clickDebounceWindow) {
                emit(it)
                lastEventTime = now
            }
        }.collect { clickEvent ->
            clickEvent.invoke()
        }
    }

    Modifier.clickable(
        enabled = enabled,
        onClickLabel = onClickLabel,
        onClick = { debounceClickState.tryEmit(onClick) },
        role = role,
        indication = if (ripple) LocalIndication.current else null,
        interactionSource = remember { MutableInteractionSource() }
    )
}
