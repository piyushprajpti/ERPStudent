package com.piyushprajpti.feature.attendance.utils

import kotlinx.datetime.LocalDate

data class SubjectAttendance(
    val subjectName: String,
    val courseCode: String,
    val professorName: String = "",
    val attendedLectures: Int,
    val totalLectures: Int,
    val trend: AttendanceTrend = AttendanceTrend.STABLE
) {
    val percentage: Float
        get() = if (totalLectures > 0) (attendedLectures.toFloat() / totalLectures) * 100 else 0f
}

enum class AttendanceTrend {
    UP, DOWN, STABLE
}

data class DailySchedule(
    val subjectName: String,
    val timeRange: String,
    val status: AttendanceStatus
)

enum class AttendanceStatus {
    PRESENT, ABSENT, LATE, NO_CLASS
}

data class AttendanceRecord(
    val date: LocalDate,
    val status: AttendanceStatus
)
