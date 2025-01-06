package com.example.data.datasource.remote.model

import com.google.gson.annotations.SerializedName

data class Astro(

    @field:SerializedName("moonset")
    val moonset: String? = null,

    @field:SerializedName("moon_illumination")
    val moonIllumination: Int? = null,

    @field:SerializedName("sunrise")
    val sunrise: String? = null,

    @field:SerializedName("moon_phase")
    val moonPhase: String? = null,

    @field:SerializedName("sunset")
    val sunset: String? = null,

    @field:SerializedName("is_moon_up")
    val isMoonUp: Int? = null,

    @field:SerializedName("is_sun_up")
    val isSunUp: Int? = null,

    @field:SerializedName("moonrise")
    val moonrise: String? = null
)