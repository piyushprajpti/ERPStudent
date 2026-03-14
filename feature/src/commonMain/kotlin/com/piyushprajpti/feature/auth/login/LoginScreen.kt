package com.piyushprajpti.feature.auth.login

import androidx.compose.foundation.clickable
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
import androidx.compose.material3.MaterialTheme
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
import com.piyushprajpti.feature.utils.loading_helper.LoadingWidget
import com.piyushprajpti.feature.utils.message_helper.composables.AlertDialog
import com.piyushprajpti.feature.utils.message_helper.composables.ToastWidget
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LoginScreen(
    onLoginClick: () -> Unit,
    viewModel: LoginViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LoadingWidget(viewModel.loadingHelper.state.value)
    ToastWidget(viewModel.messageHelper)
    AlertDialog(viewModel.messageHelper)

    fun onLoginClick() {
        viewModel.onEvent(
            LoginEvent.OnLoginClick(
                onSuccess = onLoginClick
            )
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(100.dp))

        LogoBox(
            modifier = Modifier.size(100.dp),
            imageUrl = state.tenant.logo /*"https://tse2.mm.bing.net/th/id/OIP.dJRgtbtpJ827mCUmbDVeBAHaJ4?rs=1&pid=ImgDetMain&o=7&rm=3"*/
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = state.tenant.name /*"Raffles University"*/,
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            style = MaterialTheme.typography.bodyMedium,
            color = CustomTheme.colors.primaryText
        )

//        Spacer(modifier = Modifier.height(24.dp))
//
//        Text(
//            text = "Welcome Back!",
//            fontSize = 20.sp,
//            fontWeight = FontWeight.SemiBold,
//            color = CustomTheme.colors.primaryText
//        )

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
            placeholder = "Password",
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

        Text(
            text = "Forgot Password?",
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = CustomTheme.colors.primary,
            modifier = Modifier
                .align(Alignment.End)
                .clickable {
                    viewModel.onEvent(LoginEvent.OnForgotPasswordClick)
                }
        )

        Spacer(modifier = Modifier.height(32.dp))

        PrimaryActionButton(
            text = "Login",
            onClick = { onLoginClick() },
            isLoading = state.isLoading,
            containerColor = CustomTheme.colors.primary,
            contentColor = CustomTheme.colors.white
        )

        Spacer(modifier = Modifier.height(24.dp))

        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = CustomTheme.colors.lightGray/*.copy(alpha = 0.5f)*/
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
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
                color = CustomTheme.colors.primary,
                modifier = Modifier.clickable {
                    viewModel.onEvent(LoginEvent.OnContactAdminClick)
                }
            )
        }
    }
}
