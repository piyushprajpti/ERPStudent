package com.piyushprajpti.feature.marks.grade_card

data class GradeCardState(
    val studentInfo: StudentInfo = StudentInfo(),
    val semesterResult: SemesterResult = SemesterResult(),
    val performanceOverview: List<PerformancePoint> = emptyList(),
    val isLoading: Boolean = false
)

data class StudentInfo(
    val name: String = "",
    val enrollment: String = "",
    val course: String = "",
    val semester: String = ""
)

data class SemesterResult(
    val semesterTitle: String = "",
    val systemName: String = "",
    val subjects: List<SubjectResult> = emptyList(),
    val sgpa: String = "",
    val cgpa: String = "",
    val status: String = ""
)

data class SubjectResult(
    val name: String,
    val credit: Int,
    val internal: Int,
    val external: Int,
    val total: Int,
    val grade: String,
    val gradeColor: String // To be mapped to theme colors
)

data class PerformancePoint(
    val subjectCode: String,
    val score: Float // 0.0 to 1.0 for the radar chart
)
