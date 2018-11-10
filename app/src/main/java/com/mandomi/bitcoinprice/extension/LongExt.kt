package com.mandomi.bitcoinprice.extension

import java.text.SimpleDateFormat
import java.util.*

fun Long.toDayMonthYear(): String {
    return format(this, "MMM dd, yy")
}

fun Long.toDayMonth(): String {
    return format(this, "dd. MMM")
}

fun Long.toMonthYear(): String {
    return format(this, "MMM, yy")
}

fun Long.toMinuteDayMonthYear(): String {
    return format(this, "yyyy/MM/dd HH:mm")
}

private fun format(timeSpan: Long, pattern: String): String {
    val date = Date(timeSpan * 1000L)
    val simpleDateFormat = SimpleDateFormat(pattern, Locale.getDefault())
    simpleDateFormat.timeZone = TimeZone.getTimeZone("UTC")
    return simpleDateFormat.format(date)
}