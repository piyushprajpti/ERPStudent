package com.piyushprajpti.feature.assignment.submission

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.FormatListBulleted
import androidx.compose.material.icons.automirrored.rounded.Send
import androidx.compose.material.icons.rounded.FormatBold
import androidx.compose.material.icons.rounded.FormatItalic
import androidx.compose.material.icons.rounded.InsertLink
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import com.piyushprajpti.components.PrimaryActionButton
import com.piyushprajpti.components.TopBar
import com.piyushprajpti.components.loading_helper.LoadingWidget
import com.piyushprajpti.components.message_helper.composables.AlertDialog
import com.piyushprajpti.components.message_helper.composables.ToastWidget
import com.piyushprajpti.feature.assignment.submission.components.AssignmentDetailCard
import com.piyushprajpti.feature.assignment.submission.components.FileUploadSection
import com.piyushprajpti.feature.assignment.submission.components.PreviousSubmissionCard
import com.piyushprajpti.feature.assignment.submission.components.SelectedFileItem
import com.piyushprajpti.feature.assignment.submission.components.SubmissionTypeTabs
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AssignmentSubmissionScreen(
    onBackClick: () -> Unit,
    viewModel: AssignmentSubmissionViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LoadingWidget(viewModel.loadingHelper.state.value)
    AlertDialog(viewModel.messageHelper)
    ToastWidget(viewModel.messageHelper)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(CustomTheme.colors.primaryBackground)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        TopBar(
            onBackClick = onBackClick,
            title = "Assignment Submission"
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(15.dp)
        ) {
            state.assignment?.let {
                AssignmentDetailCard(assignment = it)
            }

            state.previousSubmission?.let {
                Spacer(modifier = Modifier.height(15.dp))
                PreviousSubmissionCard(
                    submission = it,
                    onViewClick = { viewModel.onEvent(AssignmentSubmissionEvent.OnViewPreviousSubmission) }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
            SectionHeader(title = "Submission Content")
            Spacer(modifier = Modifier.height(8.dp))

            SubmissionTypeTabs(
                selectedType = state.submissionType,
                onTypeSelected = { viewModel.onEvent(AssignmentSubmissionEvent.OnSubmissionTypeChanged(it)) }
            )

            Spacer(modifier = Modifier.height(15.dp))

            when (state.submissionType) {
                SubmissionType.File -> {
                    FileUploadSection(onChooseFile = { viewModel.onEvent(AssignmentSubmissionEvent.OnChooseFile) })
                    state.selectedFiles.forEach { file ->
                        Spacer(modifier = Modifier.height(12.dp))
                        SelectedFileItem(
                            file = file,
                            onRemove = { viewModel.onEvent(AssignmentSubmissionEvent.OnRemoveFile(file)) }
                        )
                    }
                }
                SubmissionType.Text -> {
                    CustomTextArea(
                        value = state.textContent,
                        onValueChange = { viewModel.onEvent(AssignmentSubmissionEvent.OnTextChanged(it)) },
                        placeholder = "Type your response here..."
                    )
                }
                SubmissionType.Link -> {
                    CustomTextArea(
                        value = state.linkContent,
                        onValueChange = { viewModel.onEvent(AssignmentSubmissionEvent.OnLinkChanged(it)) },
                        placeholder = "Paste your link here..."
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
            SectionHeader(title = "Additional Comments")
            Spacer(modifier = Modifier.height(12.dp))

            EditorBox(
                value = state.comments,
                onValueChange = { viewModel.onEvent(AssignmentSubmissionEvent.OnCommentChanged(it)) },
                placeholder = "Type any notes for the instructor here..."
            )

            Spacer(modifier = Modifier.height(32.dp))

            PrimaryActionButton(
                text = "Submit Assignment",
                onClick = { viewModel.onEvent(AssignmentSubmissionEvent.OnSubmit) }
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Rounded.Send,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp),
                        tint = CustomTheme.colors.white
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Submit Assignment",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = CustomTheme.colors.white
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "By submitting, you agree to the Academic Integrity Policy.",
                fontSize = 11.sp,
                color = CustomTheme.colors.tertiaryText,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(15.dp))
        }
    }
}

@Composable
private fun SectionHeader(title: String) {
    Text(
        text = title,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        color = CustomTheme.colors.primaryText
    )
}

@Composable
fun EditorBox(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(15.dp))
            .background(CustomTheme.colors.secondaryBackground)
            .border(1.dp, CustomTheme.colors.outline, RoundedCornerShape(15.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {}) { Icon(Icons.Rounded.FormatBold, null, tint = CustomTheme.colors.primaryText, modifier = Modifier.size(20.dp)) }
            IconButton(onClick = {}) { Icon(Icons.Rounded.FormatItalic, null, tint = CustomTheme.colors.primaryText, modifier = Modifier.size(20.dp)) }
            IconButton(onClick = {}) { Icon(Icons.AutoMirrored.Rounded.FormatListBulleted, null, tint = CustomTheme.colors.primaryText, modifier = Modifier.size(20.dp)) }
            HorizontalDivider(modifier = Modifier.height(20.dp).width(1.dp).padding(horizontal = 8.dp), color = CustomTheme.colors.outline)
            IconButton(onClick = {}) { Icon(Icons.Rounded.InsertLink, null, tint = CustomTheme.colors.primaryText, modifier = Modifier.size(20.dp)) }
        }
        
        HorizontalDivider(color = CustomTheme.colors.outline)

        Box(modifier = Modifier.padding(15.dp).height(120.dp)) {
            if (value.isEmpty()) {
                Text(text = placeholder, color = CustomTheme.colors.tertiaryText, fontSize = 14.sp)
            }
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                textStyle = TextStyle(color = CustomTheme.colors.primaryText, fontSize = 14.sp),
                modifier = Modifier.fillMaxSize(),
                cursorBrush = SolidColor(CustomTheme.colors.primary)
            )
        }
        
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(CustomTheme.colors.secondaryBackground.copy(alpha = 0.5f))
                .padding(horizontal = 15.dp, vertical = 8.dp),
            contentAlignment = Alignment.CenterEnd
        ) {
            val wordCount = if (value.isBlank()) 0 else value.trim().split("\\s+".toRegex()).size
            Text(
                text = "$wordCount WORDS / 500 LIMIT",
                fontSize = 10.sp,
                color = CustomTheme.colors.tertiaryText,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun CustomTextArea(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(CustomTheme.colors.secondaryBackground)
            .border(1.dp, CustomTheme.colors.outline, RoundedCornerShape(12.dp))
            .padding(15.dp)
    ) {
        if (value.isEmpty()) {
            Text(text = placeholder, color = CustomTheme.colors.tertiaryText, fontSize = 14.sp)
        }
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            textStyle = TextStyle(color = CustomTheme.colors.primaryText, fontSize = 14.sp),
            modifier = Modifier.fillMaxSize(),
            cursorBrush = SolidColor(CustomTheme.colors.primary)
        )
    }
}
