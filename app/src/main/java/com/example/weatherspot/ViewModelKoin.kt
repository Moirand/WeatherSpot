package com.example.weatherspot

import com.example.weatherspot.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelKoin = module {
    viewModel { HomeViewModel(get()) }
}