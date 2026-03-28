package com.piyushprajpti.feature.attendance

import com.piyushprajpti.feature.attendance.utils.AttendanceStatus
import com.piyushprajpti.feature.attendance.utils.DailySchedule
import com.piyushprajpti.feature.attendance.utils.SubjectAttendance
import kotlinx.datetime.LocalDate

data class AttendanceState(
    val isLoading: Boolean = false,
    val minAttendanceRequirement: Float = 75f,
    val subjectAttendanceList: List<SubjectAttendance> = emptyList(),
    val dailySchedules: Map<LocalDate, List<DailySchedule>> = emptyMap(),
    val attendanceRecords: Map<LocalDate, AttendanceStatus> = emptyMap(),
    val selectedDate: LocalDate? = null,
    val overallAttendance: Float = 0f,
    val totalPresent: Int = 0,
    val totalAbsent: Int = 0,
    val totalLate: Int = 0,
    val semester: String = ""
)
