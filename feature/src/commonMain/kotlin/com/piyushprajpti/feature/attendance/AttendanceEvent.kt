package com.piyushprajpti.feature.attendance

import kotlinx.datetime.LocalDate

sealed interface AttendanceEvent {
    data class SelectDate(val date: LocalDate) : AttendanceEvent
    data object ShowAttendanceInfo : AttendanceEvent
}
