package com.example.structure.util

import java.text.SimpleDateFormat
import java.time.*
import java.util.*

/**
 * 두 날짜간의 차이
 */
fun getDateText(dateTime: Long, timezoneText: String): String {
    val nowLocalDateTime = LocalDateTime.ofInstant(
        Instant.ofEpochMilli(System.currentTimeMillis()),
        ZoneId.of(timezoneText)
    )

    val compreLocalDateTime = LocalDateTime.ofInstant(
        Instant.ofEpochMilli(dateTime),
        ZoneId.of(timezoneText)
    )

    val diff = Period.between(nowLocalDateTime.toLocalDate(), compreLocalDateTime.toLocalDate())

    return if (diff.days == 0) {
        "Today"
    } else if (diff.days == 1) {
        "Tomorrow"
    } else {
        SimpleDateFormat("EEE, dd MMM", Locale.US).format(dateTime)
    }
}