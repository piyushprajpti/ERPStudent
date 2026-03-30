package com.piyushprajpti.di

import com.piyushprajpti.feature.attendance.AttendanceViewModel
import com.piyushprajpti.feature.auth.login.LoginViewModel
import com.piyushprajpti.feature.auth.tenant.TenantViewModel
import com.piyushprajpti.feature.common.reset_password.ResetPasswordViewModel
import com.piyushprajpti.feature.common.support.SupportViewModel
import com.piyushprajpti.feature.documents.list.DocumentListViewModel
import com.piyushprajpti.feature.documents.request.DocumentRequestViewModel
import com.piyushprajpti.feature.exam.hall_ticket.ExamHallTicketViewModel
import com.piyushprajpti.feature.exam.schedule.ExamScheduleViewModel
import com.piyushprajpti.feature.fees.payment.FeesPaymentViewModel
import com.piyushprajpti.feature.fees.summary.FeesSummaryViewModel
import com.piyushprajpti.feature.home.notifications.NotificationViewModel
import com.piyushprajpti.feature.home.root.HomeViewModel
import com.piyushprajpti.feature.leave.leave_apply.ApplyLeaveViewModel
import com.piyushprajpti.feature.leave.leave_history.LeaveHistoryViewModel
import com.piyushprajpti.feature.library.book_detail.LibraryBookDetailViewModel
import com.piyushprajpti.feature.library.dashboard.LibraryDashboardViewModel
import com.piyushprajpti.feature.library.due_and_fines.LibraryDueViewModel
import com.piyushprajpti.feature.library.search.LibrarySearchViewModel
import com.piyushprajpti.feature.marks.grade_card.GradeCardViewModel
import com.piyushprajpti.feature.marks.overview.MarksOverviewViewModel
import com.piyushprajpti.feature.schedule.ScheduleViewModel
import com.piyushprajpti.feature.settings.change_password.ChangePasswordViewModel
import com.piyushprajpti.feature.settings.profile.ProfileViewModel
import com.piyushprajpti.feature.settings.root.SettingsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val featureModule = module {
    // auth
    viewModelOf(::TenantViewModel)
    viewModelOf(::LoginViewModel)

    // common
    viewModelOf(::ResetPasswordViewModel)
    viewModelOf(::SupportViewModel)

    // settings
    viewModelOf(::SettingsViewModel)
    viewModelOf(::ChangePasswordViewModel)
    viewModelOf(::ProfileViewModel)

    // schedule
    viewModelOf(::ScheduleViewModel)

    //attendance
    viewModelOf(::AttendanceViewModel)

    // home
    viewModelOf(::HomeViewModel)
    viewModelOf(::NotificationViewModel)

    // leave
    viewModelOf(::LeaveHistoryViewModel)
    viewModelOf(::ApplyLeaveViewModel)

    // fees
    viewModelOf(::FeesSummaryViewModel)
    viewModelOf(::FeesPaymentViewModel)

    // library
    viewModelOf(::LibraryDashboardViewModel)
    viewModelOf(::LibrarySearchViewModel)
    viewModelOf(::LibraryBookDetailViewModel)
    viewModelOf(::LibraryDueViewModel)

    // document
    viewModelOf(::DocumentListViewModel)
    viewModelOf(::DocumentRequestViewModel)

    // marks
    viewModelOf(::MarksOverviewViewModel)
    viewModelOf(::GradeCardViewModel)

    // exam
    viewModelOf(::ExamScheduleViewModel)
    viewModelOf(::ExamHallTicketViewModel)
}