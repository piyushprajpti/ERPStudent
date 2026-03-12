package com.piyushprajpti.feature.auth.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import com.piyushprajpti.feature.utils.CustomTextField
import com.piyushprajpti.feature.utils.LogoBox
import com.piyushprajpti.feature.utils.PrimaryActionButton
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        containerColor = CustomTheme.colors.primaryBack
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(60.dp))

            LogoBox(
                modifier = Modifier.size(100.dp),
                imageUrl = state.tenant.logo
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = state.tenant.name,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = CustomTheme.colors.primaryText
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Welcome Back!",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = CustomTheme.colors.primaryText
            )

            Spacer(modifier = Modifier.height(32.dp))

            CustomTextField(
                value = state.email,
                onValueChange = { viewModel.onEvent(LoginEvent.OnEmailChange(it)) },
                label = "Email Address",
                placeholder = "example@email.com",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )

            Spacer(modifier = Modifier.height(20.dp))

            CustomTextField(
                value = state.password,
                onValueChange = { viewModel.onEvent(LoginEvent.OnPasswordChange(it)) },
                label = "Password",
                placeholder = "••••••••••••",
                visualTransformation = if (state.isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    IconButton(onClick = { viewModel.onEvent(LoginEvent.OnTogglePasswordVisibility) }) {
                        Icon(
                            imageVector = if (state.isPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                            contentDescription = "Toggle password visibility",
                            tint = CustomTheme.colors.primaryText
                        )
                    }
                }
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "Forgot Password?",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = CustomTheme.colors.primary,
                    modifier = Modifier.clickable { viewModel.onEvent(LoginEvent.OnForgotPasswordClick) }
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            PrimaryActionButton(
                text = "Login",
                onClick = { viewModel.onEvent(LoginEvent.OnLoginClick) },
                isLoading = state.isLoading,
                containerColor = CustomTheme.colors.primary,
                contentColor = androidx.compose.ui.graphics.Color.White
            )

            Spacer(modifier = Modifier.height(24.dp))

            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 1.dp,
                color = CustomTheme.colors.lightGray.copy(alpha = 0.5f)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.clickable { viewModel.onEvent(LoginEvent.OnContactAdminClick) },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Need Help? ",
                    fontSize = 14.sp,
                    color = CustomTheme.colors.secondaryText
                )
                Text(
                    text = "Contact Admin",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = CustomTheme.colors.primary
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
