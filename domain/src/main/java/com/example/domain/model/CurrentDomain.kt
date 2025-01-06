package com.example.domain.model

data class CurrentDomain(
    val feelslikeC: Double? = null,
    val uv: Double? = null,
    val lastUpdated: String? = null,
    val feelslikeF: Double? = null,
    val windDegree: Double? = null,
    val lastUpdatedEpoch: Double? = null,
    val isDay: Double? = null,
    val precipIn: Double? = null,
    val windDir: String? = null,
    val gustMph: Double? = null,
    val tempC: Double? = null,
    val pressureIn: Double? = null,
    val gustKph: Double? = null,
    val tempF: Double? = null,
    val precipMm: Double? = null,
    val cloud: Double? = null,
    val windKph: Double? = null,
    val condition: ConditionDomain? = null,
    val windMph: Double? = null,
    val visKm: Double? = null,
    val humidity: Double? = null,
    val pressureMb: Double? = null,
    val visMiles: Double? = null
)