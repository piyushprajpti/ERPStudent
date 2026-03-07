package com.piyushprajpti.core.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration

@Composable
fun Navigation(userToken: String?) {
    val backStack = rememberNavBackStack(
        configuration = SavedStateConfiguration {
            serializersModule = serializableNavModule
        },
        if (userToken == null) NavRoutes.Tenant else NavRoutes.Home
    )

    NavDisplay(
        backStack = backStack,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = { key ->
//            authEntryProvider(key, backStack) ?: homeEntryProvider(key, backStack) ?:
            error("Unknown route: $key")
        }
    )
}
