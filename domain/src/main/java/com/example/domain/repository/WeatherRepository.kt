package com.example.domain.repository

import com.example.domain.UiState
import com.example.domain.model.response.CurrentResponseDomain
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    fun getCurrentWeather(lat: Double, long: Double): Flow<UiState<CurrentResponseDomain>>
}