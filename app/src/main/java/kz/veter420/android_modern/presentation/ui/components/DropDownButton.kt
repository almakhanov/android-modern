package kz.veter420.android_modern.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import kz.veter420.android_modern.R
import kz.veter420.android_modern.presentation.ui.theme.Colors

@Composable
fun DropDownButton(
    modifier: Modifier,
    text: String?,
    placeholder: String,
    onClick: () -> Unit
) {
    ConstraintLayout(
        modifier = modifier
            .height(44.dp)
            .fillMaxWidth(fraction = 0.68f)
            .border(
                width = 1.dp,
                color = Colors.ElementStroke,
                shape = RoundedCornerShape(10.dp)
            )
            // To make the ripple round
            .clip(shape = RoundedCornerShape(10.dp))
            .clickable {
                onClick.invoke()
            }
    ) {
        val (textField, dropDownArrow) = createRefs()
        Text(
            text = if (text.isNullOrEmpty()) placeholder else text,
            fontSize = 16.sp,
            color = if (text.isNullOrEmpty()) Colors.TextSecondary else Colors.TextPrimary,
            fontWeight = FontWeight(if (text.isNullOrEmpty()) 500 else 600),
            modifier = Modifier
                .padding(8.dp, 0.dp)
                .constrainAs(dropDownArrow) {
                    this.start.linkTo(parent.start)
                    this.top.linkTo(parent.top)
                    this.bottom.linkTo(parent.bottom)
                    this.end.linkTo(dropDownArrow.start)
                }
        )
        Image(
            painter = painterResource(id = R.drawable.ic_arrow_down),
            contentDescription = null,
            modifier = Modifier
                .padding(8.dp, 0.dp)
                .requiredSize(24.dp)
                .constrainAs(textField) {
                    this.top.linkTo(parent.top)
                    this.bottom.linkTo(parent.bottom)
                    this.end.linkTo(parent.end)
                },
            colorFilter = ColorFilter.tint(color = Colors.TextPrimary)
        )
    }
}