package com.example.data.datasource.remote.model

import com.google.gson.annotations.SerializedName

data class HourItem(

    @field:SerializedName("feelslike_c")
    val feelslikeC: Double? = null,

    @field:SerializedName("feelslike_f")
    val feelslikeF: Double? = null,

    @field:SerializedName("wind_degree")
    val windDegree: Double? = null,

    @field:SerializedName("windchill_f")
    val windchillF: Double? = null,

    @field:SerializedName("windchill_c")
    val windchillC: Double? = null,

    @field:SerializedName("temp_c")
    val tempC: Double? = null,

    @field:SerializedName("temp_f")
    val tempF: Double? = null,

    @field:SerializedName("cloud")
    val cloud: Double? = null,

    @field:SerializedName("wind_kph")
    val windKph: Double? = null,

    @field:SerializedName("wind_mph")
    val windMph: Double? = null,

    @field:SerializedName("snow_cm")
    val snowCm: Double? = null,

    @field:SerializedName("humidity")
    val humidity: Double? = null,

    @field:SerializedName("dewpoint_f")
    val dewpointF: Double? = null,

    @field:SerializedName("will_it_rain")
    val willItRain: Int? = null,

    @field:SerializedName("uv")
    val uv: Double? = null,

    @field:SerializedName("heatindex_f")
    val heatindexF: Double? = null,

    @field:SerializedName("dewpoint_c")
    val dewpointC: Double? = null,

    @field:SerializedName("is_day")
    val isDay: Int? = null,

    @field:SerializedName("precip_in")
    val precipIn: Double? = null,

    @field:SerializedName("heatindex_c")
    val heatindexC: Double? = null,

    @field:SerializedName("wind_dir")
    val windDir: String? = null,

    @field:SerializedName("gust_mph")
    val gustMph: Double? = null,

    @field:SerializedName("pressure_in")
    val pressureIn: Double? = null,

    @field:SerializedName("chance_of_rain")
    val chanceOfRain: Double? = null,

    @field:SerializedName("gust_kph")
    val gustKph: Double? = null,

    @field:SerializedName("precip_mm")
    val precipMm: Double? = null,

    @field:SerializedName("condition")
    val condition: Condition? = null,

    @field:SerializedName("will_it_snow")
    val willItSnow: Int? = null,

    @field:SerializedName("vis_km")
    val visKm: Double? = null,

    @field:SerializedName("time_epoch")
    val timeEpoch: Int? = null,

    @field:SerializedName("time")
    val time: String? = null,

    @field:SerializedName("chance_of_snow")
    val chanceOfSnow: Double? = null,

    @field:SerializedName("pressure_mb")
    val pressureMb: Double? = null,

    @field:SerializedName("vis_miles")
    val visMiles: Double? = null
)