package com.example.data.repository

import com.example.domain.UiState
import com.example.domain.datasource.RemoteDatasource
import com.example.domain.model.response.CurrentResponseDomain
import com.example.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow

class WeatherRepositoryImpl(
    private val remoteDatasource: RemoteDatasource
) : WeatherRepository {
    override fun getCurrentWeather(lat: Double, long: Double): Flow<UiState<CurrentResponseDomain>> =
        remoteDatasource.getCurrentWeather(lat, long)
}