package com.piyushprajpti.feature.exam.hall_ticket

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Share
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import com.piyushprajpti.components.TopBar
import com.piyushprajpti.components.loading_helper.LoadingWidget
import com.piyushprajpti.components.message_helper.composables.AlertDialog
import com.piyushprajpti.components.message_helper.composables.ToastWidget
import com.piyushprajpti.feature.exam.hall_ticket.components.HallTicketScheduleCard
import com.piyushprajpti.feature.exam.hall_ticket.components.StudentInfoCard
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ExamHallTicketScreen(
    onBackClick: () -> Unit,
    viewModel: ExamHallTicketViewModel = koinViewModel()
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
            Box(modifier = Modifier.fillMaxWidth()) {
                TopBar(
                    onBackClick = onBackClick,
                    title = ""
                )
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = state.examTitle,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = CustomTheme.colors.primaryText
                    )
                    Text(
                        text = state.examSubtitle,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = CustomTheme.colors.primary.copy(alpha = 0.8f)
                    )
                }
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = 100.dp)
            ) {
                item {
                    Column(modifier = Modifier.padding(horizontal = 15.dp)) {
                        Spacer(modifier = Modifier.height(10.dp))
                        StudentInfoCard(state = state)
                        
                        Spacer(modifier = Modifier.height(30.dp))
                        
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Exam Schedule",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = CustomTheme.colors.primaryText
                            )
                            Text(
                                text = "${state.examsRemaining} EXAMS REMAINING",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = CustomTheme.colors.primary
                            )
                        }
                        Spacer(modifier = Modifier.height(15.dp))
                    }
                }

                items(state.schedules) { schedule ->
                    Box(modifier = Modifier.padding(horizontal = 15.dp, vertical = 8.dp)) {
                        HallTicketScheduleCard(schedule = schedule)
                    }
                }

                item {
                    Column(modifier = Modifier.padding(15.dp)) {
                        Spacer(modifier = Modifier.height(20.dp))
                        
                        // QR Section
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(20.dp))
                                .border(1.dp, CustomTheme.colors.outline, RoundedCornerShape(20.dp))
                                .padding(25.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Box(
                                    modifier = Modifier
                                        .size(120.dp)
                                        .clip(RoundedCornerShape(15.dp))
                                        .background(Color(0xFF2D4545)) // Dark greenish from image
                                        .padding(15.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    AsyncImage(
                                        model = state.qrContent,
                                        contentDescription = "QR Code",
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .background(CustomTheme.colors.white),
                                        contentScale = ContentScale.Fit
                                    )
                                }
                                
                                Spacer(modifier = Modifier.height(15.dp))
                                
                                Text(
                                    text = "Scan to verify authenticity",
                                    fontSize = 13.sp,
                                    color = CustomTheme.colors.secondaryText
                                )
                                Text(
                                    text = state.verificationCode,
                                    fontSize = 11.sp,
                                    color = CustomTheme.colors.tertiaryText
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(30.dp))

                        // Instructions
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Info,
                                contentDescription = null,
                                tint = CustomTheme.colors.primary,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = "Important Instructions",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = CustomTheme.colors.primaryText
                            )
                        }

                        Spacer(modifier = Modifier.height(15.dp))

                        state.instructions.forEach { instruction ->
                            Row(modifier = Modifier.padding(bottom = 12.dp)) {
                                Text(
                                    text = "•",
                                    fontSize = 14.sp,
                                    color = CustomTheme.colors.primary,
                                    modifier = Modifier.padding(end = 10.dp)
                                )
                                Text(
                                    text = instruction,
                                    fontSize = 14.sp,
                                    color = CustomTheme.colors.secondaryText,
                                    lineHeight = 20.sp
                                )
                            }
                        }
                    }
                }
            }
        }

        // Bottom Buttons
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(15.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            Button(
                onClick = { viewModel.onEvent(ExamHallTicketEvent.OnDownloadPdf) },
                modifier = Modifier
                    .weight(1.5f)
                    .height(55.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                ),
                shape = RoundedCornerShape(15.dp),
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
                        Icon(Icons.Default.Download, null, tint = CustomTheme.colors.white, modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Download PDF", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = CustomTheme.colors.white)
                    }
                }
            }

            Button(
                onClick = { viewModel.onEvent(ExamHallTicketEvent.OnShare) },
                modifier = Modifier
                    .weight(1f)
                    .height(55.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = CustomTheme.colors.secondaryBackground
                ),
                shape = RoundedCornerShape(15.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, CustomTheme.colors.outline)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Share, null, tint = CustomTheme.colors.primaryText, modifier = Modifier.size(20.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Share", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = CustomTheme.colors.primaryText)
                }
            }
        }

        LoadingWidget(viewModel.loadingHelper.state.value)
        AlertDialog(viewModel.messageHelper)
        ToastWidget(viewModel.messageHelper)
    }
}
