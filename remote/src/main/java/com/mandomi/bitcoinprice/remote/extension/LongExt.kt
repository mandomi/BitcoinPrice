package com.mandomi.bitcoinprice.remote.extension

import java.text.SimpleDateFormat
import java.util.*

fun Long.toStringTime(): String {
    val date = Date(this * 1000L)
    val simpleDateFormat = SimpleDateFormat("YYYY-MM-DDThh:mm:ss", Locale.getDefault())
    simpleDateFormat.timeZone = java.util.TimeZone.getTimeZone("UTC")
    return simpleDateFormat.format(date)
}