package com.piyushprajpti.feature.marks.grade_card

sealed interface GradeCardEvent {
    data object DownloadPdf : GradeCardEvent
    data object Share : GradeCardEvent
}
