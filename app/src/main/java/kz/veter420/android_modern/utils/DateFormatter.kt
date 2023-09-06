package kz.veter420.android_modern.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


const val STANDARD_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss"
const val SALE_DATE_FORMAT = "dd MMM HH:mm"
const val DAY_FORMAT = "dd MMMM yyyy"
const val TIME_FORMAT = "HH:mm"

fun getCurrentMonthName(): String? {
    val calendar = Calendar.getInstance()
    return calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())
}

fun getCurrentMonthIndex(): Int {
    val calendar = Calendar.getInstance()
    return calendar.get(Calendar.MONTH)
}

fun getCurrentDayIndex(): Int {
    val calendar = Calendar.getInstance()
    return calendar.get(Calendar.DAY_OF_MONTH)
}

fun getCurrentHour(): Int {
    val calendar = Calendar.getInstance()
    return calendar.get(Calendar.HOUR)
}

fun getCurrentMinute(): Int {
    val calendar = Calendar.getInstance()
    return calendar.get(Calendar.MINUTE)
}

fun getCurrentYear(): Int {
    val calendar = Calendar.getInstance()
    return calendar.get(Calendar.YEAR)
}

fun getAllMonthNames(): List<String> {
    val calendar = Calendar.getInstance()
    val monthNames = mutableListOf<String>()
    for (i in 0..11) {
        calendar.set(Calendar.MONTH, i)
        calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())?.let { monthNames.add(it) }
    }
    return monthNames
}

fun getMonthNameByIndex(index: Int): String? {
    val calendar = Calendar.getInstance()
    calendar.set(Calendar.MONTH, index)
    return calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())
}

@SuppressLint("SimpleDateFormat")
fun getToday(format: String = DAY_FORMAT): String {
    val sdf = SimpleDateFormat(format)
    return sdf.format(Date())
}

@SuppressLint("SimpleDateFormat")
fun getTodayTime(format: String = TIME_FORMAT): String {
    val sdf = SimpleDateFormat(format)
    return sdf.format(Date())
}

@SuppressLint("SimpleDateFormat")
fun String.convertDateString(from: String = STANDARD_DATE_FORMAT, to: String = SALE_DATE_FORMAT): String {
    val inputFormat = SimpleDateFormat(from)
    val date = inputFormat.parse(this)
    val outputFormat = SimpleDateFormat(to)
    return date?.let { outputFormat.format(it) }.orEmpty()
}