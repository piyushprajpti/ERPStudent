package com.piyushprajpti.data.di

import com.piyushprajpti.data.local.db.LocalDatabase
import com.piyushprajpti.data.remote.service.auth.AuthApiService
import com.piyushprajpti.data.repository.auth.AuthRepositoryImpl
import com.piyushprajpti.domain.repository.auth.AuthRepository
import org.koin.dsl.module

val dataModule = module {
    single { AuthApiService(get()) }
    single { get<LocalDatabase>().tenantDao() }
    single<AuthRepository> { AuthRepositoryImpl(get(), get()) }
}
