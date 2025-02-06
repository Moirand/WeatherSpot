package com.example.data.repository

import com.example.domain.UiState
import com.example.domain.datasource.RemoteDatasource
import com.example.domain.model.response.ForecastResponseDomain
import com.example.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow

class WeatherRepositoryImpl(
    private val remoteDatasource: RemoteDatasource
) : WeatherRepository {
    override fun getSevenDaysForecast(
        lat: Double,
        long: Double,
        days: Int
    ): Flow<UiState<ForecastResponseDomain>> =
        remoteDatasource.getForecast(lat, long, days)
}