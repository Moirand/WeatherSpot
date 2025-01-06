package com.example.domain.model

data class DayDomain(
    val avgvisKm: Double? = null,
    val uv: Int? = null,
    val avgtempF: Double? = null,
    val avgtempC: Double? = null,
    val dailyChanceOfSnow: Int? = null,
    val maxtempC: Double? = null,
    val maxtempF: Double? = null,
    val mintempC: Double? = null,
    val avgvisMiles: Int? = null,
    val dailyWillItRain: Int? = null,
    val mintempF: Double? = null,
    val totalprecipIn: Double? = null,
    val totalsnowCm: Int? = null,
    val avghumidity: Int? = null,
    val condition: ConditionDomain? = null,
    val maxwindKph: Double? = null,
    val maxwindMph: Double? = null,
    val dailyChanceOfRain: Int? = null,
    val totalprecipMm: Double? = null,
    val dailyWillItSnow: Int? = null
)