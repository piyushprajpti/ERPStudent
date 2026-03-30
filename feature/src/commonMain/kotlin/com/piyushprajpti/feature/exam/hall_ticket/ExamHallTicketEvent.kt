package com.piyushprajpti.feature.exam.hall_ticket

sealed interface ExamHallTicketEvent {
    data object OnDownloadPdf : ExamHallTicketEvent
    data object OnShare : ExamHallTicketEvent
}
