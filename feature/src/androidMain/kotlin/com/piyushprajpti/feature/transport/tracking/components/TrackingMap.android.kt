package com.piyushprajpti.feature.transport.tracking.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.MarkerComposable
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState
import com.piyushprajpti.college_erp_system.theme.CustomTheme
import com.piyushprajpti.feature.transport.tracking.CameraTrigger
import com.piyushprajpti.feature.transport.tracking.TransportTrackingState
import kotlinx.coroutines.flow.Flow

@Composable
actual fun TrackingMap(
    state: TransportTrackingState,
    cameraEvents: Flow<CameraTrigger>?,
    modifier: Modifier
) {
    val busLatLng = LatLng(state.busLocation.latitude, state.busLocation.longitude)
    val collegeLatLng = LatLng(state.collegeLocation.latitude, state.collegeLocation.longitude)
    val userLatLng = LatLng(state.userLocation.latitude, state.userLocation.longitude)
    
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(userLatLng, 14.5f)
    }

    // Handle Camera Triggers from UI (Plus, Minus, My Location)
    LaunchedEffect(cameraEvents) {
        cameraEvents?.collect { trigger ->
            when (trigger) {
                CameraTrigger.FocusMyLocation -> {
                    cameraPositionState.animate(CameraUpdateFactory.newLatLngZoom(userLatLng, 16f))
                }
                CameraTrigger.ZoomIn -> {
                    cameraPositionState.animate(CameraUpdateFactory.zoomIn())
                }
                CameraTrigger.ZoomOut -> {
                    cameraPositionState.animate(CameraUpdateFactory.zoomOut())
                }
            }
        }
    }

    // Follow bus when it moves (only if camera isn't actively being moved by user)
    LaunchedEffect(state.busLocation) {
        if (!cameraPositionState.isMoving) {
            cameraPositionState.animate(CameraUpdateFactory.newLatLng(busLatLng))
        }
    }

    val routeLatLngs = remember(state.routePoints, busLatLng, userLatLng, collegeLatLng) {
        if (state.routePoints.isNotEmpty()) {
            state.routePoints.map { LatLng(it.latitude, it.longitude) }
        } else {
            listOf(busLatLng, userLatLng, collegeLatLng)
        }
    }

    val routeColor = CustomTheme.colors.primary

    GoogleMap(
        modifier = modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        uiSettings = MapUiSettings(
            zoomControlsEnabled = false,
            myLocationButtonEnabled = false,
            compassEnabled = true
        ),
        properties = MapProperties(
            isMyLocationEnabled = state.isLocationPermissionGranted,
            mapType = MapType.NORMAL
        )
    ) {
        // Draw dummy route path connecting Bus -> My Location -> College
        if (routeLatLngs.size >= 2) {
            // Casing / subtle border line for road depth
            Polyline(
                points = routeLatLngs,
                color = Color.Black.copy(alpha = 0.2f),
                width = 14f
            )
            // Main route polyline
            Polyline(
                points = routeLatLngs,
                color = routeColor,
                width = 8f
            )
        }

        // Bus Marker
        MarkerComposable(
            state = MarkerState(position = busLatLng),
            title = "Bus - ${state.routeName}"
        ) {
            BusMarker(routeName = state.routeName)
        }

        // Student's / User's Location Marker (situated in between bus and college)
        MarkerComposable(
            state = MarkerState(position = userLatLng),
            title = "My Location"
        ) {
            UserLocationMarker()
        }

        // College Destination Marker
        MarkerComposable(
            state = MarkerState(position = collegeLatLng),
            title = "College"
        ) {
            CollegeMarker()
        }
    }
}
