package com.piyushprajpti.feature.transport.tracking.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.piyushprajpti.feature.transport.tracking.CameraTrigger
import com.piyushprajpti.feature.transport.tracking.TransportTrackingState
import kotlinx.coroutines.flow.Flow

@Composable
expect fun TrackingMap(
    state: TransportTrackingState,
    cameraEvents: Flow<CameraTrigger>? = null,
    modifier: Modifier = Modifier
)
