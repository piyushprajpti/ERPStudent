package com.piyushprajpti.components

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.MonthNames
import kotlinx.datetime.format.char
import kotlinx.datetime.toLocalDateTime

object DateConverter {

    private val displayFormat = LocalDate.Format {
        day()
        char(' ')
        monthName(MonthNames.ENGLISH_ABBREVIATED)
        char(' ')
        year()
    }

    fun formatDate(dateString: String?): String {
        if (dateString.isNullOrBlank()) return "N/A"
        return try {
            val date = try {
                // Try parsing ISO Instant first (e.g., 2000-12-31T18:30:00.000Z)
                Instant.parse(dateString).toLocalDateTime(TimeZone.UTC).date
            } catch (e: Exception) {
                try {
                    // Try parsing as LocalDate (e.g., 2024-08-01)
                    LocalDate.parse(dateString)
                } catch (e2: Exception) {
                    // Fallback: take first 10 characters if it looks like "YYYY-MM-DD..."
                    val regex = Regex("^\\d{4}-\\d{2}-\\d{2}")
                    val match = regex.find(dateString)
                    if (match != null) {
                        LocalDate.parse(match.value)
                    } else {
                        throw e2
                    }
                }
            }
            date.format(displayFormat)
        } catch (e: Exception) {
            dateString
        }
    }
}
