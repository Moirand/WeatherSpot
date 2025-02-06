package com.example.domain.datasource

import com.example.domain.UiState
import com.example.domain.model.response.CurrentResponseDomain
import com.example.domain.model.response.ForecastResponseDomain
import kotlinx.coroutines.flow.Flow

interface RemoteDatasource {
    fun getCurrentWeather(lat: Double, long: Double): Flow<UiState<CurrentResponseDomain>>
    fun getForecast(lat: Double, long: Double, days: Int): Flow<UiState<ForecastResponseDomain>>
}