package com.piyushprajpti.data.di

import com.piyushprajpti.data.local.db.LocalDatabase
import com.piyushprajpti.data.remote.service.auth.AuthApiService
import com.piyushprajpti.data.remote.service.common.CommonApiService
import com.piyushprajpti.data.remote.service.user.UserApiService
import com.piyushprajpti.data.repository.AuthRepositoryImpl
import com.piyushprajpti.data.repository.CommonRepositoryImpl
import com.piyushprajpti.data.repository.UserRepositoryImpl
import com.piyushprajpti.domain.repository.AuthRepository
import com.piyushprajpti.domain.repository.CommonRepository
import com.piyushprajpti.domain.repository.UserRepository
import org.koin.dsl.module

val dataModule = module {

    // repository
    single<AuthRepository> { AuthRepositoryImpl(get(), get(), get(), get(), get()) }
    single<CommonRepository> { CommonRepositoryImpl(get()) }
    single<UserRepository> { UserRepositoryImpl(get(), get()) }

    // service
    single { AuthApiService(get()) }
    single { CommonApiService(get()) }
    single { UserApiService(get()) }

    // local database
    single { get<LocalDatabase>().tenantDao() }
    single { get<LocalDatabase>().tenantUserDao() }
    single { get<LocalDatabase>().userDao() }
}
