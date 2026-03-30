package com.piyushprajpti.feature.fees.summary

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
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Assignment
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.outlined.AccountBalanceWallet
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.WarningAmber
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import com.piyushprajpti.components.PrimaryActionButton
import com.piyushprajpti.components.TopBar
import com.piyushprajpti.components.loading_helper.LoadingWidget
import com.piyushprajpti.components.message_helper.composables.AlertDialog
import com.piyushprajpti.components.message_helper.composables.ToastWidget
import com.piyushprajpti.feature.fees.components.FeeActionItem
import com.piyushprajpti.feature.fees.components.FeeSummaryBox
import com.piyushprajpti.feature.fees.components.StudentProfileHeader
import com.piyushprajpti.feature.fees.components.TotalFeeCard
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun FeesSummaryScreen(
    onBackClick: () -> Unit,
    onPayNowClick: () -> Unit,
    viewModel: FeesSummaryViewModel = koinViewModel()
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
            .navigationBarsPadding(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        TopBar(
            onBackClick = onBackClick,
            title = "Fee Summary"
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            contentPadding = PaddingValues(15.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            item {
                StudentProfileHeader(
                    name = state.studentName,
                    details = state.studentDetails
                )
            }

            item {
                TotalFeeCard(
                    amount = state.totalFee,
                    academicYear = state.academicYear
                )
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    FeeSummaryBox(
                        label = "Paid",
                        amount = state.paidAmount,
                        color = CustomTheme.colors.success,
                        modifier = Modifier.weight(1f)
                    )
                    FeeSummaryBox(
                        label = "Pending",
                        amount = state.pendingAmount,
                        color = CustomTheme.colors.warning,
                        modifier = Modifier.weight(1f)
                    )
                    FeeSummaryBox(
                        label = "Overdue",
                        amount = state.overdueAmount,
                        color = CustomTheme.colors.error,
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            items(state.fees) { fee ->
                FeeActionItem(
                    icon = Icons.Outlined.WarningAmber,
                    title = fee.title,
                    subtitle = fee.dueDate,
                    isWarning = fee.isOverdue,
                    onClick = { viewModel.onEvent(FeesSummaryEvent.OnFeeItemClick(fee)) }
                )
            }

            item {
                FeeActionItem(
                    icon = Icons.Outlined.History,
                    title = "Fee History",
                    onClick = { viewModel.onEvent(FeesSummaryEvent.OnHistoryClick) }
                )
            }

            item {
                FeeActionItem(
                    icon = Icons.AutoMirrored.Outlined.Assignment,
                    title = "Fee Breakdown",
                    trailingIcon = Icons.Default.KeyboardArrowDown,
                    onClick = { viewModel.onEvent(FeesSummaryEvent.OnBreakdownClick) }
                )
            }

            item {
                FeeActionItem(
                    icon = Icons.Outlined.CalendarMonth,
                    title = state.nextInstallment,
                    subtitle = state.nextInstallmentDate,
                    onClick = { }
                )
            }

            item {
                Spacer(modifier = Modifier.height(80.dp))
            }
        }

        PrimaryActionButton(
            text = "Pay Now (${state.payNowAmount})",
            onClick = onPayNowClick,
            modifier = Modifier
                .padding(15.dp),
            content = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Outlined.AccountBalanceWallet,
                        contentDescription = null,
                        tint = CustomTheme.colors.white
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Pay Now (${state.payNowAmount})",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = CustomTheme.colors.white
                    )
                }
            }
        )
    }
}
