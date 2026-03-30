package com.piyushprajpti.feature.exam.schedule

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import com.piyushprajpti.components.TopBar
import com.piyushprajpti.components.loading_helper.LoadingWidget
import com.piyushprajpti.components.message_helper.composables.AlertDialog
import com.piyushprajpti.components.message_helper.composables.ToastWidget
import com.piyushprajpti.feature.exam.components.ExamCard
import com.piyushprajpti.feature.exam.components.ExamSummaryCard
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ExamScheduleScreen(
    onBackClick: () -> Unit,
    onHallTicketClick: () -> Unit,
    viewModel: ExamScheduleViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(CustomTheme.colors.primaryBackground)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            TopBar(
                onBackClick = onBackClick,
                title = "Exam Schedule"
            )

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = 100.dp)
            ) {
                item {
                    Column(modifier = Modifier.padding(horizontal = 15.dp)) {
                        Text(
                            text = "Academic Year ${state.academicYear}",
                            fontSize = 14.sp,
                            color = CustomTheme.colors.secondaryText,
                            modifier = Modifier.padding(bottom = 20.dp)
                        )

                        ExamSummaryCard(
                            summary = state.summary,
                            session = state.session
                        )

                        Spacer(modifier = Modifier.height(25.dp))

                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            items(state.examTypes) { type ->
                                val isSelected = state.selectedExamType == type
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(25.dp))
                                        .background(
                                            if (isSelected) CustomTheme.colors.primary
                                            else CustomTheme.colors.secondaryBackground
                                        )
                                        .clickable {
                                            viewModel.onEvent(ExamScheduleEvent.OnExamTypeSelected(type))
                                        }
                                        .padding(horizontal = 20.dp, vertical = 10.dp)
                                ) {
                                    Text(
                                        text = type,
                                        fontSize = 15.sp,
                                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                        color = if (isSelected) CustomTheme.colors.white else CustomTheme.colors.primaryText
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(30.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.Bottom
                        ) {
                            Text(
                                text = "Upcoming Exams",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = CustomTheme.colors.primaryText
                            )
                            Text(
                                text = "View all Calendar",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Medium,
                                color = CustomTheme.colors.primary,
                                modifier = Modifier.clickable { /* Handle click */ }
                            )
                        }

                        Spacer(modifier = Modifier.height(15.dp))
                    }
                }

                items(state.exams) { exam ->
                    Box(modifier = Modifier.padding(horizontal = 15.dp, vertical = 8.dp)) {
                        ExamCard(exam = exam)
                    }
                }
            }
        }

        LoadingWidget(viewModel.loadingHelper.state.value)
        AlertDialog(viewModel.messageHelper)
        ToastWidget(viewModel.messageHelper)

        // Floating Download Button
        Button(
            onClick = onHallTicketClick,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 20.dp)
                .fillMaxWidth(0.9f)
                .height(55.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
            ),
            shape = RoundedCornerShape(30.dp),
            contentPadding = PaddingValues()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.linearGradient(
                            colors = listOf(
                                CustomTheme.colors.primary,
                                CustomTheme.colors.primary.copy(alpha = 0.8f)
                            )
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Download,
                        contentDescription = null,
                        tint = CustomTheme.colors.white,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "Download Hall Ticket",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = CustomTheme.colors.white
                    )
                }
            }
        }
    }
}
