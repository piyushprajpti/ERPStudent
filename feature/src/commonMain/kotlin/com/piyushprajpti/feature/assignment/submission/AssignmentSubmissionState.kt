package com.piyushprajpti.feature.assignment.submission

data class AssignmentSubmissionState(
    val isLoading: Boolean = false,
    val assignment: AssignmentDetail? = null,
    val previousSubmission: PreviousSubmission? = null,
    val submissionType: SubmissionType = SubmissionType.File,
    val selectedFiles: List<SelectedFile> = emptyList(),
    val comments: String = "",
    val textContent: String = "",
    val linkContent: String = ""
)

data class AssignmentDetail(
    val id: String,
    val title: String,
    val description: String,
    val marks: Int,
    val dueDate: String,
    val allowedFormats: List<String>,
    val imageUrl: String? = null,
    val status: String = "ACTIVE"
)

data class PreviousSubmission(
    val date: String,
    val time: String
)

data class SelectedFile(
    val name: String,
    val size: String,
    val status: String = "Ready to upload"
)

enum class SubmissionType {
    File, Text, Link
}