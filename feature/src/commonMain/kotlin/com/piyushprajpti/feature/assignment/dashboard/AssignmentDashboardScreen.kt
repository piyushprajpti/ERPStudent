package com.piyushprajpti.feature.assignment.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import com.piyushprajpti.components.TopBar
import com.piyushprajpti.components.loading_helper.LoadingWidget
import com.piyushprajpti.components.message_helper.composables.AlertDialog
import com.piyushprajpti.components.message_helper.composables.ToastWidget
import com.piyushprajpti.feature.assignment.dashboard.components.AssignmentCard
import com.piyushprajpti.feature.assignment.dashboard.components.AssignmentTabs
import com.piyushprajpti.feature.assignment.dashboard.components.SummaryCard
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AssignmentDashboardScreen(
    onBackClick: () -> Unit,
    onAssignmentClick: (assignmentId: String) -> Unit,
    viewModel: AssignmentDashboardViewModel = koinViewModel()
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
            title = "Assignments"
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            item {
                SummaryCard(
                    upcomingCount = state.upcomingThisWeekCount,
                    modifier = Modifier.padding(horizontal = 15.dp)
                )
            }

            item {
                AssignmentTabs(
                    selectedTab = state.selectedTab,
                    onTabSelected = { viewModel.onEvent(AssignmentDashboardEvent.OnTabSelected(it)) }
                )
            }

            if (state.selectedTab == AssignmentTab.Pending) {
                val overdue = state.assignments.filter { it.status == AssignmentStatus.Overdue }
                if (overdue.isNotEmpty()) {
                    item {
                        SectionHeader(title = "Overdue")
                    }
                    items(overdue) { assignment ->
                        AssignmentCard(
                            assignment = assignment,
                            onClick = { onAssignmentClick(assignment.id) },
                            modifier = Modifier.padding(horizontal = 15.dp)
                        )
                    }
                }

                val upcoming = state.assignments.filter { it.status == AssignmentStatus.Upcoming }
                if (upcoming.isNotEmpty()) {
                    item {
                        SectionHeader(title = "Upcoming")
                    }
                    items(upcoming) { assignment ->
                        AssignmentCard(
                            assignment = assignment,
                            onClick = { onAssignmentClick(assignment.id) },
                            modifier = Modifier.padding(horizontal = 15.dp)
                        )
                    }
                }
            } else {
                items(state.assignments) { assignment ->
                    AssignmentCard(
                        assignment = assignment,
                        onClick = { onAssignmentClick(assignment.id) },
                        modifier = Modifier.padding(horizontal = 15.dp)
                    )
                }
            }

            item { Spacer(modifier = Modifier.height(15.dp)) }
        }
    }
}

@Composable
private fun SectionHeader(
    title: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = title,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        color = CustomTheme.colors.primaryText,
        modifier = modifier.padding(horizontal = 15.dp)
    )
}
