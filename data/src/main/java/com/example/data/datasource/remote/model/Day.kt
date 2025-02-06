package com.example.data.datasource.remote.model

import com.google.gson.annotations.SerializedName

data class Day(

    @field:SerializedName("avgvis_km")
    val avgvisKm: Double? = null,

    @field:SerializedName("uv")
    val uv: Double? = null,

    @field:SerializedName("avgtemp_f")
    val avgtempF: Double? = null,

    @field:SerializedName("avgtemp_c")
    val avgtempC: Double? = null,

    @field:SerializedName("daily_chance_of_snow")
    val dailyChanceOfSnow: Double? = null,

    @field:SerializedName("maxtemp_c")
    val maxtempC: Double? = null,

    @field:SerializedName("maxtemp_f")
    val maxtempF: Double? = null,

    @field:SerializedName("mintemp_c")
    val mintempC: Double? = null,

    @field:SerializedName("avgvis_miles")
    val avgvisMiles: Double? = null,

    @field:SerializedName("daily_will_it_rain")
    val dailyWillItRain: Double? = null,

    @field:SerializedName("mintemp_f")
    val mintempF: Double? = null,

    @field:SerializedName("totalprecip_in")
    val totalprecipIn: Double? = null,

    @field:SerializedName("totalsnow_cm")
    val totalsnowCm: Double? = null,

    @field:SerializedName("avghumidity")
    val avghumidity: Double? = null,

    @field:SerializedName("condition")
    val condition: Condition? = null,

    @field:SerializedName("maxwind_kph")
    val maxwindKph: Double? = null,

    @field:SerializedName("maxwind_mph")
    val maxwindMph: Double? = null,

    @field:SerializedName("daily_chance_of_rain")
    val dailyChanceOfRain: Double? = null,

    @field:SerializedName("totalprecip_mm")
    val totalprecipMm: Double? = null,

    @field:SerializedName("daily_will_it_snow")
    val dailyWillItSnow: Double? = null
)