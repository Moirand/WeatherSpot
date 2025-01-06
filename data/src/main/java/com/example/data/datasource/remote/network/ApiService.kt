package com.example.data.datasource.remote.network

import com.example.data.datasource.remote.model.response.CurrentResponse
import com.example.data.datasource.remote.model.response.ForecastResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("current.json")
    suspend fun getCurrentWeather(
        @Query("key") key: String? = "5770daaac41b4a6ab8031239232305",
        @Query("q") q: String,
        @Query("lang") lang: String? = null
    ): CurrentResponse

    @GET("forecast.json")
    suspend fun getForecast(
        @Query("key") key: String? = "5770daaac41b4a6ab8031239232305",
        @Query("q") q: String,
        @Query("days") days: Int
    ): ForecastResponse
}