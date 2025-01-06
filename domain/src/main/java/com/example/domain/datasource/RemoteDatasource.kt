package com.example.domain.datasource

import com.example.domain.UiState
import com.example.domain.model.response.CurrentResponseDomain
import kotlinx.coroutines.flow.Flow

interface RemoteDatasource {
    fun getCurrentWeather(lat: Double, long: Double): Flow<UiState<CurrentResponseDomain>>
}