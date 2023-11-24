package kz.veter420.android_modern.presentation.utils

import androidx.compose.ui.graphics.Color

fun String.hexToColor() = Color(android.graphics.Color.parseColor(this))