package com.example.domain.model.response

import com.example.domain.model.CurrentDomain
import com.example.domain.model.ForecastDomain
import com.example.domain.model.LocationDomain

data class ForecastResponseDomain(
	val current: CurrentDomain? = null,
	val location: LocationDomain? = null,
	val forecast: ForecastDomain? = null
)