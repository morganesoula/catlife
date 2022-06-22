package com.ms.catlife.util

import android.content.Context
import com.ms.catlife.R
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.Calendar
import java.util.Locale

object DateFormatter {

    @Suppress("SameReturnValue")
    fun longToString(milliseconds: Long?, context: Context): String? {
        milliseconds?.let {
            val currentTime = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC).times(1000)
            if (it >= currentTime) return context.getString(R.string.inconsistent)

            val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val calendar = Calendar.getInstance()

            calendar.timeInMillis = it
            return formatter.format(calendar.time)
        }

        return null
    }

    fun getDefaultDateInMillis(): Long {
        val cal = Calendar.getInstance()
        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DATE)
        cal.clear()
        cal.set(year, month, day)
        return cal.timeInMillis
    }
}