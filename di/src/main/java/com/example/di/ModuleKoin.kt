package com.example.di

import com.example.data.datasource.remote.RemoteDatasourceImpl
import com.example.data.datasource.remote.network.ApiConfig
import com.example.data.datasource.remote.network.ApiService
import com.example.data.repository.WeatherRepositoryImpl
import com.example.domain.datasource.RemoteDatasource
import com.example.domain.repository.WeatherRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val moduleKoin = module {
    // Repository
    single<WeatherRepository> { WeatherRepositoryImpl(get()) }

    // Datasource
    single<RemoteDatasource> { RemoteDatasourceImpl(get()) }
    single<ApiService> { ApiConfig.provideApiService(androidContext()) }

//    single<DataStore<Preferences>> { androidContext().dataStore }
}