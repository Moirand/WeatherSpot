package com.example.domain.repository

import com.example.domain.UiState
import com.example.domain.model.response.ForecastResponseDomain
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    fun getSevenDaysForecast(lat: Double, long: Double, days: Int = 7): Flow<UiState<ForecastResponseDomain>>
}