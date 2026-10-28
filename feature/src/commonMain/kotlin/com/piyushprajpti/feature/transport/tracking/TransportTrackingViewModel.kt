package com.piyushprajpti.feature.transport.tracking

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piyushprajpti.components.loading_helper.LoadingHelper
import com.piyushprajpti.components.message_helper.MessageHelper
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class TransportTrackingViewModel : ViewModel() {

    val loadingHelper = LoadingHelper()
    val messageHelper = MessageHelper()

    private val _cameraEvents = MutableSharedFlow<CameraTrigger>(extraBufferCapacity = 16)
    val cameraEvents = _cameraEvents.asSharedFlow()

    private val _state = MutableStateFlow(TransportTrackingState())
    val state = _state.onStart {
        loadData()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = TransportTrackingState()
    )

    private var isDataLoaded = false

    private fun loadData() {
        // Prevent redundant data loading
        if (isDataLoaded) return
        isDataLoaded = true

        val dummyRoute = listOf(
            Location(23.0450, 72.5680), // Bus location
            Location(23.0415, 72.5685),
            Location(23.0380, 72.5695),
            Location(23.0350, 72.5702),
            Location(23.0335, 72.5710), // User location (in between bus & college)
            Location(23.0300, 72.5718),
            Location(23.0265, 72.5722),
            Location(23.0240, 72.5717),
            Location(23.0225, 72.5714)  // College location
        )

        _state.update {
            it.copy(
                routeName = "Route 42",
                distance = "2.4 km",
                eta = "8 mins",
                driverName = "Vikram Singh",
                driverRating = "4.8",
                driverImage = "https://cdn-icons-png.flaticon.com/512/15297/15297661.png",
                busNumber = "GJ-01-AX-1234",
                currentSpeed = "35 km/h",
                isAlertEnabled = true,
                collegeLocation = Location(23.0225, 72.5714),
                busLocation = Location(23.0450, 72.5680),
                userLocation = Location(23.0335, 72.5710),
                routePoints = dummyRoute
            )
        }
    }

    fun onEvent(event: TransportTrackingEvent) {
        when (event) {
            is TransportTrackingEvent.ToggleAlert -> {
                // Only update if value actually changed
                if (_state.value.isAlertEnabled != event.enabled) {
                    _state.update { it.copy(isAlertEnabled = event.enabled) }
                }
            }

            TransportTrackingEvent.Refresh -> {
                loadData()
            }

            TransportTrackingEvent.FocusMyLocation -> {
                _cameraEvents.tryEmit(CameraTrigger.FocusMyLocation)
            }
            TransportTrackingEvent.ZoomIn -> {
                _cameraEvents.tryEmit(CameraTrigger.ZoomIn)
            }
            TransportTrackingEvent.ZoomOut -> {
                _cameraEvents.tryEmit(CameraTrigger.ZoomOut)
            }
            is TransportTrackingEvent.PermissionResult -> {
                _state.update { it.copy(isLocationPermissionGranted = event.isGranted) }
            }
        }
    }
}
