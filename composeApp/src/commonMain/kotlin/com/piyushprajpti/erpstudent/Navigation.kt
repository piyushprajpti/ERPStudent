package com.piyushprajpti.erpstudent

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import com.piyushprajpti.core.navigation.NavRoutes
import com.piyushprajpti.core.navigation.serializableNavModule
import com.piyushprajpti.feature.auth.authEntryProvider
import com.piyushprajpti.feature.mainEntryProvider

@Composable
fun Navigation(userToken: String?) {
    val backStack = rememberNavBackStack(
        configuration = SavedStateConfiguration {
            serializersModule = serializableNavModule
        },
        if (userToken == null) NavRoutes.Tenant else NavRoutes.Profile
    )

    NavDisplay(
        backStack = backStack,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = { key ->
            authEntryProvider(key, backStack)
                ?: mainEntryProvider(key, backStack)
                ?: error("Unknown route: $key")
        }
    )
}
