package com.piyushprajpti.feature.fees.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import com.piyushprajpti.feature.fees.payment.FeeHead
import com.piyushprajpti.feature.fees.payment.PaymentMethod
import com.piyushprajpti.feature.fees.payment.ReceiptInfo

@Composable
fun StudentProfileCard(
    name: String,
    id: String,
    semester: String,
    imageUrl: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(15.dp))
            .background(CustomTheme.colors.secondaryBackground)
            .padding(15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .background(CustomTheme.colors.primaryBackground)
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = null,
                modifier = Modifier.fillMaxSize().padding(10.dp),
                tint = CustomTheme.colors.secondaryText
            )
        }
        Spacer(modifier = Modifier.width(15.dp))
        Column {
            Text(
                text = "STUDENT PROFILE",
                fontSize = 10.sp,
                color = CustomTheme.colors.primary,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = name,
                fontSize = 18.sp,
                color = CustomTheme.colors.primaryText,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "ID: $id • $semester",
                fontSize = 12.sp,
                color = CustomTheme.colors.secondaryText
            )
        }
    }
}

@Composable
fun FeeHeadItem(
    feeHead: FeeHead,
    onToggle: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(CustomTheme.colors.secondaryBackground)
            .border(
                width = 1.dp,
                color = if (feeHead.isSelected) CustomTheme.colors.primary else Color.Transparent,
                shape = RoundedCornerShape(12.dp)
            )
            .clickable { onToggle() }
            .padding(horizontal = 10.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = feeHead.isSelected,
            onCheckedChange = { onToggle() },
            colors = CheckboxDefaults.colors(
                checkedColor = CustomTheme.colors.primary,
                uncheckedColor = CustomTheme.colors.secondaryText,
                checkmarkColor = Color.White
            )
        )
        Text(
            text = feeHead.name,
            modifier = Modifier.weight(1f),
            color = CustomTheme.colors.primaryText,
            fontSize = 14.sp
        )
        Text(
            text = "₹${feeHead.amount.toInt()}",
            color = CustomTheme.colors.primaryText,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun TotalAmountCard(amount: Double) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(CustomTheme.colors.secondaryBackground)
            .padding(15.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Total Amount Payable",
            color = CustomTheme.colors.secondaryText,
            fontSize = 14.sp
        )
        Text(
            text = "₹${amount.toInt()}",
            color = CustomTheme.colors.primary,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun PaymentMethodItem(
    method: PaymentMethod,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .width(100.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(CustomTheme.colors.secondaryBackground)
            .border(
                width = 1.dp,
                color = if (isSelected) CustomTheme.colors.primary else Color.Transparent,
                shape = RoundedCornerShape(12.dp)
            )
            .clickable { onClick() }
            .padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = when(method.id) {
                "upi" -> Icons.Default.AccountBalanceWallet
                "netbanking" -> Icons.Default.AccountBalance
                "debit" -> Icons.Default.CreditCard
                "credit" -> Icons.Default.CreditCard
                else -> Icons.Default.Payments
            },
            contentDescription = null,
            tint = if (isSelected) CustomTheme.colors.primary else CustomTheme.colors.secondaryText,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = method.name,
            color = if (isSelected) CustomTheme.colors.primary else CustomTheme.colors.secondaryText,
            fontSize = 12.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun PaymentOverlay(
    title: String,
    onDismiss: () -> Unit,
    content: @Composable ColumnScope.() -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.6f))
            .clickable(enabled = true) { onDismiss() }
            .padding(20.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(CustomTheme.colors.secondaryBackground)
                .clickable(enabled = false) { }
                .padding(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = CustomTheme.colors.primaryText
                )
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close",
                    modifier = Modifier.clickable { onDismiss() },
                    tint = CustomTheme.colors.secondaryText
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            content()
        }
    }
}

@Composable
fun SummaryRow(label: String, value: String, isBold: Boolean = false) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            color = CustomTheme.colors.secondaryText,
            fontSize = 14.sp
        )
        Text(
            text = value,
            color = CustomTheme.colors.primaryText,
            fontSize = if (isBold) 16.sp else 14.sp,
            fontWeight = if (isBold) FontWeight.Bold else FontWeight.Normal
        )
    }
}

@Composable
fun SuccessOverlayContent(
    receiptInfo: ReceiptInfo,
    onDownload: () -> Unit,
    onHome: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(CustomTheme.colors.primary.copy(alpha = 0.2f)),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(CustomTheme.colors.primary),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(30.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Payment Successful",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = CustomTheme.colors.primaryText
        )
        Text(
            text = "Your fee has been processed\nsuccessfully.",
            fontSize = 14.sp,
            color = CustomTheme.colors.secondaryText,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(25.dp))
        
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(CustomTheme.colors.primaryBackground.copy(alpha = 0.5f))
                .padding(15.dp)
        ) {
            SummaryRow("Receipt No:", receiptInfo.receiptNo)
            SummaryRow("Date & Time:", receiptInfo.dateAndTime)
            SummaryRow("Paid Via:", receiptInfo.paidVia)
        }
        
        Spacer(modifier = Modifier.height(25.dp))
        
        androidx.compose.material3.Button(
            onClick = onDownload,
            modifier = Modifier.fillMaxWidth().height(50.dp),
            shape = RoundedCornerShape(12.dp),
            colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                containerColor = CustomTheme.colors.primary
            )
        ) {
            Icon(Icons.Default.Download, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Download Receipt", fontWeight = FontWeight.Bold)
        }
        
        Spacer(modifier = Modifier.height(12.dp))
        
        androidx.compose.material3.Button(
            onClick = onHome,
            modifier = Modifier.fillMaxWidth().height(50.dp),
            shape = RoundedCornerShape(12.dp),
            colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                containerColor = CustomTheme.colors.primaryBackground
            )
        ) {
            Text("Go to Home", fontWeight = FontWeight.Bold, color = CustomTheme.colors.primaryText)
        }
    }
}
