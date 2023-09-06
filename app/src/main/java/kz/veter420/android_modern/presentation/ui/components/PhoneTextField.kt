package kz.veter420.android_modern.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kz.veter420.android_modern.R
import kz.veter420.android_modern.presentation.ui.theme.Colors
import kz.veter420.android_modern.utils.MaskVisualTransformation


object KzPhoneNumber {
    const val MASK = "+7 (###) ###-##-##"
    const val LENGTH = 10
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PhoneTextField(phoneNumber: MutableState<TextFieldValue>) {
    var hasFocus by remember { mutableStateOf(false) }
    val inputLength = KzPhoneNumber.LENGTH
    BasicTextField(
        value = phoneNumber.value,
        onValueChange = {
            if (it.text.length <= inputLength && it.text.find { !it.isDigit() } == null) {
                phoneNumber.value = it
            }
        },
        textStyle = TextStyle(
            color = Colors.TextPrimary,
            fontWeight = FontWeight(500),
            fontSize = 16.sp
        ),
        singleLine = true,
        visualTransformation = MaskVisualTransformation(KzPhoneNumber.MASK),
        cursorBrush = SolidColor(Colors.PrimaryMain),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = Modifier
            .height(44.dp)
            .fillMaxWidth()
            .absolutePadding(0.dp)
            .border(
                width = 1.dp,
                color = if (hasFocus) Colors.PrimaryMain else Colors.ElementStroke,
                shape = RoundedCornerShape(10.dp)
            )
            .background(color = Colors.BackgroundSecondaryDark, RoundedCornerShape(10.dp))
            .onFocusChanged { focusState -> hasFocus = focusState.hasFocus }
    ) {
        TextFieldDefaults.TextFieldDecorationBox(
            value = phoneNumber.value.text,
            innerTextField = it,
            interactionSource = remember { MutableInteractionSource() },
            visualTransformation = VisualTransformation.None,
            enabled = true,
            singleLine = true,
            placeholder = { Text(text = stringResource(id = R.string.enter_phone_number)) },
            contentPadding = TextFieldDefaults.textFieldWithoutLabelPadding(
                start = 8.dp,
                top = 10.dp,
                end = 8.dp,
                bottom = 10.dp,
            )
        )
    }
}
