package com.piyushprajpti.leave.leave_history

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import com.piyushprajpti.feature.components.TopBar
import com.piyushprajpti.feature.components.loading_helper.LoadingWidget
import com.piyushprajpti.feature.components.message_helper.composables.AlertDialog
import com.piyushprajpti.feature.components.message_helper.composables.ToastWidget
import com.piyushprajpti.leave.components.LeaveHistoryCard
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LeaveHistoryScreen(
    onBackClick: () -> Unit,
    onAddClick: () -> Unit,
    viewModel: LeaveHistoryViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LoadingWidget(viewModel.loadingHelper.state.value)
    AlertDialog(viewModel.messageHelper)
    ToastWidget(viewModel.messageHelper)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(CustomTheme.colors.primaryBackground)
            .statusBarsPadding()
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            TopBar(
                onBackClick = onBackClick,
                title = "Leave History"
            )

            LazyRow(
                modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp),
                contentPadding = PaddingValues(horizontal = 15.dp),
                horizontalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                items(LeaveStatus.entries) { status ->
                    FilterTab(
                        status = status,
                        isSelected = state.selectedStatus == status,
                        onClick = { viewModel.onEvent(LeaveHistoryEvent.OnStatusSelected(status)) }
                    )
                }
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(
                    start = 15.dp,
                    end = 15.dp,
                    top = 15.dp,
                    bottom = 100.dp
                ),
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                items(state.filteredLeaves) { leave ->
                    LeaveHistoryCard(leave = leave)
                }
            }
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .navigationBarsPadding()
                .padding(20.dp)
                .size(56.dp)
                .clip(CircleShape)
                .background(CustomTheme.colors.primary)
                .clickable { onAddClick() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add Leave",
                tint = CustomTheme.colors.white
            )
        }
    }
}

@Composable
private fun FilterTab(
    status: LeaveStatus,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier.clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = status.label,
            fontSize = 14.sp,
            color = if (isSelected) CustomTheme.colors.primary else CustomTheme.colors.secondaryText,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
        )

        if (isSelected) {
            Spacer(modifier = Modifier.height(4.dp))
            Box(
                modifier = Modifier
                    .width(20.dp)
                    .height(2.dp)
                    .clip(RoundedCornerShape(2.dp))
                    .background(CustomTheme.colors.primary)
            )
        }
    }
}
