package com.example.data.datasource.remote

import com.example.data.datasource.remote.model.response.ErrorResponse
import com.example.data.datasource.remote.network.ApiService
import com.example.data.toDomain
import com.example.domain.UiState
import com.example.domain.datasource.RemoteDatasource
import com.example.domain.model.response.CurrentResponseDomain
import com.example.domain.model.response.ForecastResponseDomain
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class RemoteDatasourceImpl(
    private val apiService: ApiService
) : RemoteDatasource {
    override fun getCurrentWeather(
        lat: Double,
        long: Double
    ): Flow<UiState<CurrentResponseDomain>> =
        flow {
            emit(UiState.Loading())
            emit(UiState.Success(apiService.getCurrentWeather(q = "$lat,$long").toDomain()))
        }.catch { e ->
            if (e is HttpException) {
                val errorResponse = e.response()?.errorBody()?.string()
                val json = Gson().fromJson(errorResponse, ErrorResponse::class.java)
                emit(UiState.Error(json.message))
            } else {
                emit(UiState.Error(e.message ?: "An unknown error occurred"))
            }
        }

    override fun getForecast(
        lat: Double,
        long: Double,
        days: Int
    ): Flow<UiState<ForecastResponseDomain>> =
        flow {
            emit(UiState.Loading())
            emit(UiState.Success(apiService.getForecast(q = "$lat,$long", days = days).toDomain()))
        }.catch { e ->
            if (e is HttpException) {
                val errorResponse = e.response()?.errorBody()?.string()
                val json = Gson().fromJson(errorResponse, ErrorResponse::class.java)
                emit(UiState.Error(json.message))
            } else {
                emit(UiState.Error(e.message ?: "An unknown error occurred"))
            }
        }
}