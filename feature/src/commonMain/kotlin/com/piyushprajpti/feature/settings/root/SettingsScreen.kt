package com.piyushprajpti.feature.settings.root

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ContactSupport
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.automirrored.filled.OpenInNew
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import com.piyushprajpti.feature.settings.util.SettingsDivider
import com.piyushprajpti.feature.settings.util.SettingsItem
import com.piyushprajpti.feature.settings.util.SettingsSection
import com.piyushprajpti.feature.settings.util.SettingsSwitchItem
import com.piyushprajpti.feature.utils.loading_helper.LoadingWidget
import com.piyushprajpti.feature.utils.message_helper.composables.AlertDialog
import com.piyushprajpti.feature.utils.message_helper.composables.ToastWidget
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SettingsScreen(
    onProfileClick: () -> Unit,
    onSupportClick: () -> Unit,
    viewModel: SettingsViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val statusBarPadding = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
    val uriHandler = LocalUriHandler.current

    LoadingWidget(viewModel.loadingHelper.state.value)
    ToastWidget(viewModel.messageHelper)
    AlertDialog(viewModel.messageHelper)

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(CustomTheme.colors.primaryBackground),
        contentPadding = PaddingValues(
            top = 15.dp + statusBarPadding,
            bottom = 15.dp,
            start = 15.dp,
            end = 15.dp
        ),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        item {
            SettingsSection(title = "ACCOUNT") {
                SettingsItem(
                    title = "View Profile",
                    subtitle = "Manage your personal information",
                    leadingIcon = Icons.Default.Person,
                    trailingIcon = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    onClick = { onProfileClick() }
                )
                SettingsDivider()
                SettingsItem(
                    title = "Change Password",
                    leadingIcon = Icons.Default.Lock,
                    trailingIcon = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    onClick = { viewModel.onEvent(SettingsEvent.OnChangePasswordClick) }
                )
                SettingsDivider()
                SettingsItem(
                    title = "Contact Support",
                    leadingIcon = Icons.AutoMirrored.Filled.ContactSupport,
                    trailingIcon = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    onClick = { onSupportClick() }
                )
                SettingsDivider()
                SettingsItem(
                    title = "Logout",
                    leadingIcon = Icons.AutoMirrored.Filled.Logout,
                    titleColor = CustomTheme.colors.primary,
                    trailingIcon = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    onClick = { viewModel.onEvent(SettingsEvent.OnLogoutClick) }
                )
            }
        }

        item {
            SettingsSection(title = "NOTIFICATIONS") {
                SettingsSwitchItem(
                    title = "All Notifications",
                    icon = Icons.Default.NotificationsActive,
                    checked = state.allNotifications,
                    onCheckedChange = { viewModel.onEvent(SettingsEvent.ToggleAllNotifications(it)) }
                )

                Text(
                    text = "Categories",
                    fontSize = 13.sp,
                    color = CustomTheme.colors.secondaryText,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(CustomTheme.colors.outline.copy(alpha = 0.3f))
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                )

                SettingsSwitchItem(
                    title = "Attendance Alerts",
                    checked = state.attendanceAlerts,
                    onCheckedChange = { viewModel.onEvent(SettingsEvent.ToggleAttendanceAlerts(it)) }
                )
                SettingsDivider()
                SettingsSwitchItem(
                    title = "Class Alerts",
                    checked = state.classAlerts,
                    onCheckedChange = { viewModel.onEvent(SettingsEvent.ToggleClassAlerts(it)) }
                )
                SettingsDivider()
                SettingsSwitchItem(
                    title = "Announcements",
                    checked = state.announcements,
                    onCheckedChange = { viewModel.onEvent(SettingsEvent.ToggleAnnouncements(it)) }
                )
            }
        }

        item {
            SettingsSection(title = "ABOUT") {
                SettingsItem(
                    title = "App Version",
                    trailingText = state.appVersion,
                )
                SettingsDivider()
                SettingsItem(
                    title = "Terms of Service",
                    trailingIcon = Icons.AutoMirrored.Filled.OpenInNew,
                    onClick = { uriHandler.openUri("https://s8ul.live/") }
                )
                SettingsDivider()
                SettingsItem(
                    title = "Privacy Policy",
                    trailingIcon = Icons.AutoMirrored.Filled.OpenInNew,
                    onClick = { uriHandler.openUri("https://s8ul.live/") }
                )
            }
        }

        item {
            Spacer(modifier = Modifier.height(80.dp))
        }
    }
}
