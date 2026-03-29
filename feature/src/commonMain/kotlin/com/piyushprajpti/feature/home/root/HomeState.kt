package com.piyushprajpti.feature.home.root

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Assignment
import androidx.compose.material.icons.automirrored.outlined.ExitToApp
import androidx.compose.material.icons.automirrored.outlined.LibraryBooks
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.Event
import androidx.compose.material.icons.outlined.Grade
import androidx.compose.material.icons.outlined.HistoryEdu
import androidx.compose.material.icons.outlined.HomeWork
import androidx.compose.material.icons.outlined.Payments
import androidx.compose.material.icons.outlined.School
import androidx.compose.ui.graphics.vector.ImageVector

data class HomeState(
    val userName: String = "Arjun Mehta",
    val profilePictureUrl: String? = null,
    val currentDate: String = "Monday, 23rd October 2023",
    val attendanceAlert: AttendanceAlert? = AttendanceAlert(
        message = "Your OS attendance is 68%. Minimum 75% required.",
        percentage = 68
    ),
    val nextClass: NextClass? = NextClass(
        subject = "Data Structures",
        professor = "Prof. Sharma",
        startTime = "10:30 AM",
        room = "Room 402",
        startsInMinutes = 15
    ),
    val stats: HomeStats = HomeStats(
        attendancePercentage = 86,
        feesDue = 500,
        booksCount = 5
    ),
    val quickActions: List<QuickAction> = listOf(
        QuickAction.Notices, QuickAction.Leave, QuickAction.Fees, QuickAction.Library, QuickAction.Documents,
        QuickAction.Marks, QuickAction.Exam, QuickAction.Hostel, QuickAction.Events, QuickAction.Assignment,
        QuickAction.Transport
    ),
    val todaySchedule: List<ScheduleItem> = listOf(
        ScheduleItem("12:00 PM", "DBMS Theory", "Hall 301"),
        ScheduleItem("02:00 PM", "Operating Systems", "Lab 1"),
        ScheduleItem("04:00 PM", "Software Engineering Lab", "Lab 4")
    ),
    val isLoading: Boolean = false
)

data class AttendanceAlert(
    val message: String,
    val percentage: Int
)

data class NextClass(
    val subject: String,
    val professor: String,
    val startTime: String,
    val room: String,
    val startsInMinutes: Int
)

data class HomeStats(
    val attendancePercentage: Int,
    val feesDue: Int,
    val booksCount: Int
)

sealed class QuickAction(val title: String, val icon: ImageVector) {
    data object Notices : QuickAction("Notices", Icons.Outlined.CalendarMonth)
    data object Leave : QuickAction("Leave", Icons.AutoMirrored.Outlined.ExitToApp)
    data object Fees : QuickAction("Fees", Icons.Outlined.Payments)
    data object Library : QuickAction("Library", Icons.AutoMirrored.Outlined.LibraryBooks)
    data object Documents : QuickAction("Documents", Icons.Outlined.Description)
    data object Marks : QuickAction("Marks", Icons.Outlined.Grade)
    data object Exam : QuickAction("Exam", Icons.Outlined.HistoryEdu)
    data object Hostel : QuickAction("Hostel", Icons.Outlined.HomeWork)
    data object Events : QuickAction("Events", Icons.Outlined.Event)
    data object Assignment : QuickAction("Assignment", Icons.AutoMirrored.Outlined.Assignment)
    data object Transport : QuickAction("Transport", Icons.Outlined.School)
}

data class ScheduleItem(
    val time: String,
    val subject: String,
    val location: String
)
