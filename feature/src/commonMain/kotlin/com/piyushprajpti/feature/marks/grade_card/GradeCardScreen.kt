package com.piyushprajpti.feature.marks.grade_card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Reply
import androidx.compose.material.icons.filled.Download
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.piyushprajpti.feature.marks.components.GradeTable
import com.piyushprajpti.feature.marks.components.InfoCard
import com.piyushprajpti.feature.marks.components.PerformanceRadarChart
import com.piyushprajpti.feature.marks.components.ResultHeader
import com.piyushprajpti.feature.marks.components.ResultSummary
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun GradeCardScreen(
    onBackClick: () -> Unit,
    viewModel: GradeCardViewModel = koinViewModel()
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
            title = "Grade Card"
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(15.dp),
            verticalArrangement = Arrangement.spacedBy(25.dp)
        ) {
            ResultHeader(
                semesterTitle = state.semesterResult.semesterTitle,
                systemName = state.semesterResult.systemName
            )

            InfoCard(studentInfo = state.studentInfo)

            GradeTable(subjects = state.semesterResult.subjects)

            ResultSummary(result = state.semesterResult)

            PerformanceRadarChart(performancePoints = state.performanceOverview)

            Row(
                modifier = Modifier.fillMaxWidth().padding(bottom = 15.dp),
                horizontalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                ActionButton(
                    text = "Download PDF",
                    icon = Icons.Default.Download,
                    containerColor = CustomTheme.colors.primary,
                    contentColor = CustomTheme.colors.white,
                    onClick = { viewModel.onEvent(GradeCardEvent.DownloadPdf) },
                    modifier = Modifier.weight(1f)
                )
                ActionButton(
                    text = "Share",
                    icon = Icons.AutoMirrored.Filled.Reply,
                    containerColor = CustomTheme.colors.primary.copy(alpha = 0.1f),
                    contentColor = CustomTheme.colors.primary,
                    onClick = { viewModel.onEvent(GradeCardEvent.Share) },
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
private fun ActionButton(
    text: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    containerColor: Color,
    contentColor: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier.height(55.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
        shape = RoundedCornerShape(15.dp),
        contentPadding = PaddingValues(horizontal = 15.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(imageVector = icon, contentDescription = null, modifier = Modifier.size(20.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = text,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
