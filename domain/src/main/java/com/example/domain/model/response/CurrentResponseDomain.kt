package com.example.domain.model.response

import com.example.domain.model.CurrentDomain
import com.example.domain.model.LocationDomain

data class CurrentResponseDomain(
    val current: CurrentDomain? = null,
    val location: LocationDomain? = null
)