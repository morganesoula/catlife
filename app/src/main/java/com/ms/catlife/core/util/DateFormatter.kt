package com.ms.catlife.core.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateFormatter {

    fun longToDate(timestamp: Long, locale: Locale): String = SimpleDateFormat("d MMMM yyyy", locale).format(timestamp)
}