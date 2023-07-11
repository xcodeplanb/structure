package com.example.structure.util

import java.text.SimpleDateFormat
import java.time.*
import java.util.*

/**
 * 두 날짜 차이
 */
fun getDateText(dateTime: Long, timezoneText: String): String {
    val nowLocalDateTime = LocalDateTime.ofInstant(
        Instant.ofEpochMilli(System.currentTimeMillis()),
        ZoneId.of(timezoneText)
    )

    val compareLocalDateTime = LocalDateTime.ofInstant(
        Instant.ofEpochMilli(dateTime),
        ZoneId.of(timezoneText)
    )

    val diff = Period.between(nowLocalDateTime.toLocalDate(), compareLocalDateTime.toLocalDate())

    return when (diff.days) {
        0 -> {
            "Today"
        }
        1 -> {
            "Tomorrow"
        }
        else -> {
            SimpleDateFormat("EEE, dd MMM", Locale.US).format(dateTime)
        }
    }
}

/**
 * 공백 제거
 */
fun String.removeAllWhitespaces(): String {
    return this.replace("\\s+".toRegex(), "")
}