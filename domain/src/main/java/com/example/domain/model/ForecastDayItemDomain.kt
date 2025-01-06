package com.example.domain.model

data class ForecastDayItemDomain(
    val date: String? = null,
    val astro: AstroDomain? = null,
    val dateEpoch: Int? = null,
    val hour: List<HourItemDomain?>? = null,
    val day: DayDomain? = null
)