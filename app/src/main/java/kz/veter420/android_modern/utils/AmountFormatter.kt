package kz.veter420.android_modern.utils

fun formatNumberWithSpaces(number: Long): String {
    val numberString = number.toString()
    val stringBuilder = StringBuilder()
    var count = 0
    for (i in numberString.indices.reversed()) {
        stringBuilder.append(numberString[i])
        count++
        if (count % 3 == 0 && i != 0) {
            stringBuilder.append(" ")
        }
    }
    return stringBuilder.reverse().toString()
}