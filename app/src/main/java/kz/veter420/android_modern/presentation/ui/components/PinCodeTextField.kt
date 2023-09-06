package kz.veter420.android_modern.presentation.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import kz.veter420.android_modern.presentation.ui.theme.Colors
import kz.veter420.android_modern.utils.Keyboard
import kz.veter420.android_modern.utils.keyboardAsState


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun PinCodeTextField(onComplete: (String) -> Unit) {

    val isKeyboardOpen by keyboardAsState(Keyboard.Opened)

    val pinCodeLength = 6
    val focusManager = LocalFocusManager.current

    val pinCode = remember { mutableStateOf(String()) }

    val pinCodeItems = mutableListOf<MutableState<String>>()
    val focusRequesters = mutableListOf<FocusRequester>()

    val focusRequesterFactory = remember { FocusRequester.createRefs() }
    val kCallables = focusRequesterFactory::class.members.toList()

    for (i in 0 until pinCodeLength) {
        (kCallables.getOrNull(i)
            ?.call(focusRequesterFactory) as? FocusRequester)
            ?.let { focusRequester ->
                pinCodeItems.add(remember { mutableStateOf(String()) })
                focusRequesters.add(focusRequester)
            }
    }

    LaunchedEffect(pinCodeItems[pinCodeLength - 1].value) {
        pinCode.value = ""
        pinCodeItems.forEach {
            pinCode.value += it.value
        }
        if (pinCode.value.length == pinCodeLength) {
            onComplete.invoke(pinCode.value)
            focusManager.clearFocus()
        }
    }

    LaunchedEffect(Unit) {
        focusRequesters[0].requestFocus()
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .height(44.dp)
            .fillMaxWidth()
            .absolutePadding(0.dp)
            .border(
                width = 1.dp,
                color = if (isKeyboardOpen == Keyboard.Opened) Colors.PrimaryMain else Colors.ElementStroke,
                shape = RoundedCornerShape(10.dp)
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.zIndex(0f)
        ) {
            focusRequesters.forEachIndexed { index, focusRequester ->
                OtpChar(
                    otpItem = pinCodeItems[index],
                    modifier = Modifier
                        .zIndex(0f)
                        .focusRequester(focusRequester)
                        .focusProperties {
                            previous = if (index == 0) {
                                focusRequesters[index]
                            } else {
                                focusRequesters[index - 1]
                            }
                            next = if (index == pinCodeLength - 1) {
                                focusRequesters[index]
                            } else {
                                focusRequesters[index + 1]
                            }
                        }
                )
                if (pinCodeLength / 2 == index + 1) {
                    Spacer(modifier = Modifier.width(16.dp))
                }
            }
        }
        Box(
            modifier = Modifier
                .clickable {
                    for (ind in pinCodeLength - 1 downTo 0) {
                        if (pinCodeItems[ind].value.isEmpty()) {
                            focusRequesters[ind].requestFocus()
                        }
                    }
                    if (pinCode.value.length == pinCodeLength) {
                        focusRequesters[pinCodeLength - 1].requestFocus()
                    }
                }
                .height(44.dp)
                .fillMaxWidth()
                .zIndex(1f)
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun OtpChar(otpItem: MutableState<String>, modifier: Modifier = Modifier) {
    val pattern = remember { Regex("^[^\\t]*\$") }
    var (text, setText) = remember { mutableStateOf("") }
    otpItem.value = text
    val maxChar = 1
    val focusManager = LocalFocusManager.current

    LaunchedEffect(
        key1 = text,
    ) {
        if (text.isNotEmpty()) {
            focusManager.moveFocus(
                focusDirection = FocusDirection.Next,
            )
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BasicTextField(
            value = text,
            onValueChange = {
                if (it.length <= maxChar && ((it.isEmpty() || it.matches(pattern) && it.find { !it.isDigit() } == null))) {
                    setText(it)
                }
            },
            modifier = modifier
                .width(16.dp)
                .onKeyEvent {
                    if (it.key == Key.Backspace) {
                        focusManager.moveFocus(FocusDirection.Previous)
                        return@onKeyEvent true
                    }
                    if (text.isNotEmpty() && text.find { !it.isDigit() } == null) {
                        focusManager.moveFocus(FocusDirection.Next)
                        return@onKeyEvent true
                    }
                    return@onKeyEvent false
                },
            textStyle = LocalTextStyle.current.copy(
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                color = Colors.TextPrimary,
                fontWeight = FontWeight(500)
            ),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Number
            )
        ) {
            TextFieldDefaults.TextFieldDecorationBox(
                value = text,
                innerTextField = it,
                interactionSource = remember { MutableInteractionSource() },
                visualTransformation = VisualTransformation.None,
                enabled = true,
                singleLine = true,
                placeholder = {
                    Text(
                        text = "—",
                        color = Colors.TextPrimary,
                        modifier = Modifier.alpha(0.5f)
                    )
                },
                contentPadding = TextFieldDefaults.textFieldWithoutLabelPadding(
                    start = 0.dp,
                    top = 0.dp,
                    end = 0.dp,
                    bottom = 0.dp,
                ),
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Colors.TextPrimary,
                    backgroundColor = Transparent,
                    unfocusedIndicatorColor = Transparent,
                    focusedIndicatorColor = Transparent
                )
            )
        }
    }
}