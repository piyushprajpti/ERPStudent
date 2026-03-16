package com.piyushprajpti.feature.common.reset_password

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import com.piyushprajpti.feature.utils.CustomTextField
import com.piyushprajpti.feature.utils.LogoBox
import com.piyushprajpti.feature.utils.PrimaryActionButton
import com.piyushprajpti.feature.utils.TopBar
import com.piyushprajpti.feature.utils.loading_helper.LoadingWidget
import com.piyushprajpti.feature.utils.message_helper.composables.AlertDialog
import com.piyushprajpti.feature.utils.message_helper.composables.ToastWidget
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ResetPasswordScreen(
    onBackClick: () -> Unit,
    onSuccess: () -> Unit,
    viewModel: ResetPasswordViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val focusManager = LocalFocusManager.current

    var isPasswordVisible by remember { mutableStateOf(false) }
    var isConfirmPasswordVisible by remember { mutableStateOf(false) }

    LoadingWidget(viewModel.loadingHelper.state.value)
    ToastWidget(viewModel.messageHelper)
    AlertDialog(viewModel.messageHelper)

    fun onActionClick() {
        viewModel.onEvent(ResetPasswordEvent.OnActionClick(onSuccess = onSuccess))
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBar(
            onBackClick = onBackClick,
            title = if (state.resetToken.isNotBlank()) "Reset Password"
            else if (state.isOtpSent) "Verify OTP"
            else "Forgot Password"
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectTapGestures(onTap = {
                        focusManager.clearFocus()
                    })
                }
                .verticalScroll(rememberScrollState())
                .padding(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LogoBox(
                modifier = Modifier.size(100.dp),
                imageUrl = state.tenant.logo
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = state.tenant.name,
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                style = MaterialTheme.typography.bodyMedium,
                color = CustomTheme.colors.primaryText
            )

            Spacer(modifier = Modifier.height(15.dp))

            Text(
                text = if (state.resetToken.isNotBlank()) {
                    "Set your new password below."
                } else if (state.isOtpSent) {
                    "Enter the 6-digit OTP sent to your email."
                } else {
                    "Enter your registered email. You will receive an OTP if your email is already registered with us."
                },
                fontSize = 14.sp,
                color = CustomTheme.colors.secondaryText,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(30.dp))

            CustomTextField(
                value = state.email,
                onValueChange = { viewModel.onEvent(ResetPasswordEvent.OnEmailChange(it)) },
                label = "Email Address",
                placeholder = "example@email.com",
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = if (state.isOtpSent) ImeAction.Next else ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                        onActionClick()
                    }
                ),
                enabled = !state.isOtpSent
            )

            if (state.isOtpSent) {
                Spacer(modifier = Modifier.height(20.dp))

                CustomTextField(
                    value = state.otp,
                    onValueChange = {
                        if (it.length <= 6) viewModel.onEvent(
                            ResetPasswordEvent.OnOtpChange(
                                it
                            )
                        )
                    },
                    label = "OTP",
                    placeholder = "Enter 6-digit OTP",
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = if (state.resetToken.isNotBlank()) ImeAction.Next else ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.clearFocus()
                            onActionClick()
                        }
                    ),
                    enabled = state.resetToken.isBlank()
                )
            }

            if (state.resetToken.isNotBlank()) {
                Spacer(modifier = Modifier.height(20.dp))

                CustomTextField(
                    value = state.password,
                    onValueChange = { viewModel.onEvent(ResetPasswordEvent.OnPasswordChange(it)) },
                    label = "New Password",
                    placeholder = "Enter new password",
                    visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Next
                    ),
                    trailingIcon = {
                        IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                            Icon(
                                imageVector = if (isPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                contentDescription = "Toggle password visibility",
                                tint = CustomTheme.colors.primaryText
                            )
                        }
                    }
                )

                Spacer(modifier = Modifier.height(20.dp))

                CustomTextField(
                    value = state.confirmPassword,
                    onValueChange = {
                        viewModel.onEvent(
                            ResetPasswordEvent.OnConfirmPasswordChange(
                                it
                            )
                        )
                    },
                    label = "Confirm Password",
                    placeholder = "Confirm new password",
                    visualTransformation = if (isConfirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.clearFocus()
                            onActionClick()
                        }
                    ),
                    trailingIcon = {
                        IconButton(
                            onClick = { isConfirmPasswordVisible = !isConfirmPasswordVisible }
                        ) {
                            Icon(
                                imageVector = if (isConfirmPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                contentDescription = "Toggle password visibility",
                                tint = CustomTheme.colors.primaryText
                            )
                        }
                    }
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            PrimaryActionButton(
                text = if (state.resetToken.isNotBlank()) "Reset Password" else if (state.isOtpSent) "Verify OTP" else "Send OTP",
                onClick = {
                    focusManager.clearFocus()
                    onActionClick()
                },
                containerColor = CustomTheme.colors.primary,
                contentColor = CustomTheme.colors.white
            )

        }
    }
}
