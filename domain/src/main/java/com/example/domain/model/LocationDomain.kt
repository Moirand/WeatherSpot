package com.example.domain.model

data class LocationDomain(
    val localtime: String? = null,
    val country: String? = null,
    val localtimeEpoch: Int? = null,
    val name: String? = null,
    val lon: Double? = null,
    val region: String? = null,
    val lat: Double? = null,
    val tzId: String? = null
)