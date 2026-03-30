package com.piyushprajpti.feature.fees.payment

import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.piyushprajpti.feature.fees.components.FeeHeadItem
import com.piyushprajpti.feature.fees.components.PaymentMethodItem
import com.piyushprajpti.feature.fees.components.PaymentOverlay
import com.piyushprajpti.feature.fees.components.StudentProfileCard
import com.piyushprajpti.feature.fees.components.SuccessOverlayContent
import com.piyushprajpti.feature.fees.components.SummaryRow
import com.piyushprajpti.feature.fees.components.TotalAmountCard
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun FeesPaymentScreen(
    onBackClick: () -> Unit,
    viewModel: FeesPaymentViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(CustomTheme.colors.primaryBackground)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            TopBar(
                onBackClick = onBackClick,
                title = "Fee Payment"
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
                    .padding(15.dp)
            ) {
                Text(
                    text = "Payment Form",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = CustomTheme.colors.primaryText
                )
                Spacer(modifier = Modifier.height(15.dp))

                StudentProfileCard(
                    name = state.studentName,
                    id = state.studentId,
                    semester = state.semester,
                    imageUrl = state.studentImage
                )

                Spacer(modifier = Modifier.height(25.dp))

                Text(
                    text = "Select Fee Heads",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = CustomTheme.colors.primaryText
                )
                Spacer(modifier = Modifier.height(10.dp))

                state.feeHeads.forEach { head ->
                    FeeHeadItem(
                        feeHead = head,
                        onToggle = { viewModel.onEvent(FeesPaymentEvent.ToggleFeeHead(head.id)) }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }

                Spacer(modifier = Modifier.height(20.dp))

                TotalAmountCard(amount = state.totalAmount)

                Spacer(modifier = Modifier.height(25.dp))

                Text(
                    text = "Select Payment Method",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = CustomTheme.colors.primaryText
                )
                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    state.paymentMethods.forEach { method ->
                        PaymentMethodItem(
                            method = method,
                            isSelected = state.selectedPaymentMethodId == method.id,
                            onClick = { viewModel.onEvent(FeesPaymentEvent.SelectFeesPaymentMethod(method.id)) }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(30.dp))

                Button(
                    onClick = { viewModel.onEvent(FeesPaymentEvent.ReviewFeesPayment) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = CustomTheme.colors.primary
                    )
                ) {
                    Text(
                        text = "Review Payment",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }
                
                Spacer(modifier = Modifier.height(20.dp))
            }
        }

        // State 2: Confirmation Overlay
        if (state.overlayType == PaymentOverlayType.Confirmation) {
            PaymentOverlay(
                title = "Payment Summary",
                onDismiss = { viewModel.onEvent(FeesPaymentEvent.DismissOverlay) }
            ) {
                state.selectedFeeHeads.forEach { head ->
                    SummaryRow(head.name, "₹${head.amount.toInt()}")
                }
                SummaryRow("Service Tax (2%)", "₹${state.serviceTax.toInt()}")
                
                Spacer(modifier = Modifier.height(15.dp))
                Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(CustomTheme.colors.outline))
                Spacer(modifier = Modifier.height(15.dp))
                
                SummaryRow("Payable Amount", "₹${state.payableAmount.toInt()}", isBold = true)
                
                Spacer(modifier = Modifier.height(25.dp))
                
                Text(
                    text = "I agree to the Terms & Conditions and understand that fee once paid is non-refundable.",
                    fontSize = 12.sp,
                    color = CustomTheme.colors.secondaryText
                )
                
                Spacer(modifier = Modifier.height(20.dp))
                
                Button(
                    onClick = { viewModel.onEvent(FeesPaymentEvent.ProceedToPay) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = CustomTheme.colors.primary
                    )
                ) {
                    Text(
                        text = "Proceed to Pay ₹${state.payableAmount.toInt()}",
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        // State 3: Success State
        if (state.overlayType == PaymentOverlayType.Success && state.receiptInfo != null) {
            PaymentOverlay(
                title = "Success State",
                onDismiss = { viewModel.onEvent(FeesPaymentEvent.DismissOverlay) }
            ) {
                SuccessOverlayContent(
                    receiptInfo = state.receiptInfo!!,
                    onDownload = { viewModel.onEvent(FeesPaymentEvent.DownloadReceipt) },
                    onHome = { 
                        viewModel.onEvent(FeesPaymentEvent.GoToHome)
                        onBackClick()
                    }
                )
            }
        }

        LoadingWidget(viewModel.loadingHelper.state.value)
        AlertDialog(viewModel.messageHelper)
        ToastWidget(viewModel.messageHelper)
    }
}
