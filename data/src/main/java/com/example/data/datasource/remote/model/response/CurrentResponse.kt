package com.example.data.datasource.remote.model.response

import com.example.data.datasource.remote.model.Current
import com.example.data.datasource.remote.model.Location
import com.google.gson.annotations.SerializedName

data class CurrentResponse(

	@field:SerializedName("current")
    val current: Current? = null,

	@field:SerializedName("location")
    val location: Location? = null
)