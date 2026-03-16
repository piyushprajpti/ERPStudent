package com.piyushprajpti.erpstudent

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import com.piyushprajpti.core.navigation.NavRoutes
import com.piyushprajpti.core.navigation.serializableNavModule
import com.piyushprajpti.feature.auth.authEntryProvider
import com.piyushprajpti.feature.common.commonEntryProvider
import com.piyushprajpti.feature.mainEntryProvider

@Composable
fun Navigation(authState: AuthState) {
    val backStack = rememberNavBackStack(
        configuration = SavedStateConfiguration {
            serializersModule = serializableNavModule
        },
        NavRoutes.Splash
    )

    // Industry Standard: Reactive Navigation Logic
    // This handles transitions automatically when authState changes.
    LaunchedEffect(authState) {
        when (authState) {
            is AuthState.Authenticated -> {
                // If we are on Splash, move to the main flow (Home)
                if (backStack.lastOrNull() == NavRoutes.Splash) {
                    backStack.removeLast()
                    backStack.add(NavRoutes.Profile)
                }
            }

            is AuthState.Unauthenticated -> {
                // If we are on Splash, move to the auth flow (Tenant)
                if (backStack.lastOrNull() == NavRoutes.Splash) {
                    backStack.removeLast()
                    backStack.add(NavRoutes.Tenant)
                }
            }

            AuthState.Loading -> {
                // Stay on Splash screen
            }
        }
    }

    val providers = remember {
        listOf(
            ::authEntryProvider,
            ::commonEntryProvider,
            ::mainEntryProvider
        )
    }

    NavDisplay(
        backStack = backStack,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = { key ->
            when (key) {
                is NavRoutes.Splash -> NavEntry(key) {
                    SplashScreen()
                }

                else -> {
                    providers.firstNotNullOfOrNull { provider ->
                        provider(key, backStack)
                    } ?: error("Unknown route: $key")
                }
            }
        }
    )
}
