package com.piyushprajpti.erpstudent.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.piyushprajpti.data.local.datastore.createDataStore
import com.piyushprajpti.data.local.datastore.dataStoreFileName
import com.piyushprajpti.erpstudent.db.RoomDBFactory
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single { RoomDBFactory(androidContext()).createDatabase() }
    single<DataStore<Preferences>> {
        createDataStore {
            androidContext().filesDir.resolve(dataStoreFileName).absolutePath
        }
    }
}
