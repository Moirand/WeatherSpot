package com.example.data.datasource.remote.model

import com.google.gson.annotations.SerializedName

data class Forecast(

    @field:SerializedName("forecastday")
    val forecastday: List<ForecastDayItem?>? = null
)