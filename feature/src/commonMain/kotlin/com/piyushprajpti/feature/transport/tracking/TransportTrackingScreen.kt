package com.piyushprajpti.feature.transport.tracking

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import com.piyushprajpti.components.TopBar
import com.piyushprajpti.components.loading_helper.LoadingWidget
import com.piyushprajpti.components.message_helper.composables.AlertDialog
import com.piyushprajpti.components.message_helper.composables.ToastWidget
import com.piyushprajpti.feature.transport.tracking.components.BusMarker
import com.piyushprajpti.feature.transport.tracking.components.CollegeMarker
import com.piyushprajpti.feature.transport.tracking.components.DriverInfoCard
import com.piyushprajpti.feature.transport.tracking.components.TrackingStatsPill
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun TransportTrackingScreen(
    onBackClick: () -> Unit,
    viewModel: TransportTrackingViewModel = koinViewModel()
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
            .navigationBarsPadding()
    ) {
        // Simulated Map Background
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            CustomTheme.colors.secondaryBackground,
                            CustomTheme.colors.primaryBackground
                        )
                    )
                )
        )

        // Markers Overlay
        Box(modifier = Modifier.fillMaxSize()) {
            CollegeMarker(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 180.dp)
            )

            BusMarker(
                routeName = state.routeName,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(bottom = 20.dp)
            )
        }

        // UI Layer
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                TopBar(
                    onBackClick = onBackClick,
                    title = "Live Tracking - ${state.routeName}"
                )
                
                IconButton(
                    onClick = { /* More options */ },
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = 5.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "More",
                        tint = CustomTheme.colors.primaryText
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 15.dp, vertical = 20.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.End
            ) {
                // This spacer allows the user to scroll down to see the controls if the screen is small
                Spacer(modifier = Modifier.height(300.dp))

                // Map Zoom Controls
                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    MapControlButton(icon = Icons.Default.Add) { viewModel.onEvent(TransportTrackingEvent.ZoomIn) }
                    MapControlButton(icon = Icons.Default.Remove) { viewModel.onEvent(TransportTrackingEvent.ZoomOut) }
                }

                Spacer(modifier = Modifier.height(15.dp))

                // My Location Button
                MapControlButton(icon = Icons.Default.MyLocation) { viewModel.onEvent(TransportTrackingEvent.FocusMyLocation) }

                Spacer(modifier = Modifier.height(25.dp))

                // Stats Pill
                TrackingStatsPill(
                    distance = state.distance,
                    eta = state.eta,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(20.dp))

                // Driver & Bus Details Card
                DriverInfoCard(
                    state = state,
                    onToggleAlert = { viewModel.onEvent(TransportTrackingEvent.ToggleAlert(it)) }
                )
            }
        }
    }
}

@Composable
private fun MapControlButton(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
        modifier = Modifier
            .size(50.dp)
            .clip(CircleShape)
            .background(CustomTheme.colors.secondaryBackground.copy(alpha = 0.95f))
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = CustomTheme.colors.primaryText,
            modifier = Modifier.size(24.dp)
        )
    }
}
