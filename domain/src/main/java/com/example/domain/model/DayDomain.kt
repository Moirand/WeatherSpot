package com.example.domain.model

data class DayDomain(
    val avgvisKm: Double? = null,
    val uv: Double? = null,
    val avgtempF: Double? = null,
    val avgtempC: Double? = null,
    val dailyChanceOfSnow: Double? = null,
    val maxtempC: Double? = null,
    val maxtempF: Double? = null,
    val mintempC: Double? = null,
    val avgvisMiles: Double? = null,
    val dailyWillItRain: Double? = null,
    val mintempF: Double? = null,
    val totalprecipIn: Double? = null,
    val totalsnowCm: Double? = null,
    val avghumidity: Double? = null,
    val condition: ConditionDomain? = null,
    val maxwindKph: Double? = null,
    val maxwindMph: Double? = null,
    val dailyChanceOfRain: Double? = null,
    val totalprecipMm: Double? = null,
    val dailyWillItSnow: Double? = null
)