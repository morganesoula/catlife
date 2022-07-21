package com.ms.catlife.core.util

import android.content.Context
import android.os.Build
import java.text.SimpleDateFormat
import java.util.Locale

object DateFormatter {

    private fun longToDate(timestamp: Long, locale: Locale): String =
        SimpleDateFormat("d MMMM yyyy", locale).format(timestamp)

    fun dateAccordingToLocale(context: Context, timestamp: Long): String {
        return longToDate(
            timestamp, if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                context.resources.configuration.locales[0]
            } else {
                @Suppress("DEPRECATION") context.resources.configuration.locale
            }
        )
    }
}