package com.example.data

import com.example.data.datasource.remote.model.Astro
import com.example.data.datasource.remote.model.Condition
import com.example.data.datasource.remote.model.Current
import com.example.data.datasource.remote.model.Day
import com.example.data.datasource.remote.model.Forecast
import com.example.data.datasource.remote.model.ForecastDayItem
import com.example.data.datasource.remote.model.HourItem
import com.example.data.datasource.remote.model.Location
import com.example.data.datasource.remote.model.response.CurrentResponse
import com.example.data.datasource.remote.model.response.ForecastResponse
import com.example.domain.model.AstroDomain
import com.example.domain.model.ConditionDomain
import com.example.domain.model.CurrentDomain
import com.example.domain.model.DayDomain
import com.example.domain.model.ForecastDayItemDomain
import com.example.domain.model.ForecastDomain
import com.example.domain.model.HourItemDomain
import com.example.domain.model.LocationDomain
import com.example.domain.model.response.CurrentResponseDomain
import com.example.domain.model.response.ForecastResponseDomain

fun CurrentResponse.toDomain(): CurrentResponseDomain =
    CurrentResponseDomain(
        current = current?.toDomain(),
        location = location?.toDomain()
    )

fun ForecastResponse.toDomain(): ForecastResponseDomain =
    ForecastResponseDomain(
        current = current?.toDomain(),
        location = location?.toDomain(),
        forecast = forecast?.toDomain()
    )

private fun Astro.toDomain(): AstroDomain =
    AstroDomain(
        moonset = moonset,
        moonIllumination = moonIllumination,
        sunrise = sunrise,
        moonPhase = moonPhase,
        sunset = sunset,
    )

private fun Condition.toDomain(): ConditionDomain =
    ConditionDomain(
        code = code,
        icon = icon,
        text = text
    )

private fun Current.toDomain(): CurrentDomain =
    CurrentDomain(
        feelslikeC = feelslikeC,
        uv = uv,
        lastUpdated = lastUpdated,
        feelslikeF = feelslikeF,
        windDegree = windDegree,
        lastUpdatedEpoch = lastUpdatedEpoch,
        isDay = isDay,
        precipIn = precipIn,
        windDir = windDir,
        gustMph = gustMph,
        tempC = tempC,
        pressureIn = pressureIn,
        gustKph = gustKph,
        tempF = tempF,
        precipMm = precipMm,
        cloud = cloud,
        windKph = windKph,
        condition = condition?.toDomain(),
        windMph = windMph,
        visKm = visKm,
        humidity = humidity,
        pressureMb = pressureMb,
        visMiles = visMiles
    )

private fun Day.toDomain(): DayDomain =
    DayDomain(
        avgvisKm = avgvisKm,
        uv = uv,
        avgtempF = avgtempF,
        avgtempC = avgtempC,
        dailyChanceOfSnow = dailyChanceOfSnow,
        maxtempC = maxtempC,
        maxtempF = maxtempF,
        mintempC = mintempC,
        avgvisMiles = avgvisMiles,
        dailyWillItRain = dailyWillItRain,
        mintempF = mintempF,
        totalprecipIn = totalprecipIn,
        totalsnowCm = totalsnowCm,
        avghumidity = avghumidity,
        condition = condition?.toDomain(),
        maxwindKph = maxwindKph,
        maxwindMph = maxwindMph,
        dailyChanceOfRain = dailyChanceOfRain,
        totalprecipMm = totalprecipMm,
        dailyWillItSnow = dailyWillItSnow
    )

private fun Forecast.toDomain(): ForecastDomain =
    ForecastDomain(
        forecastday = forecastday?.map { it?.toDomain() }
    )

private fun ForecastDayItem.toDomain(): ForecastDayItemDomain =
    ForecastDayItemDomain(
        date = date,
        astro = astro?.toDomain(),
        dateEpoch = dateEpoch,
        hour = hour?.map { it?.toDomain() },
        day = day?.toDomain()
    )

private fun HourItem.toDomain(): HourItemDomain =
    HourItemDomain(
        feelslikeC = feelslikeC,
        feelslikeF = feelslikeF,
        windDegree = windDegree,
        windchillF = windchillF,
        windchillC = windchillC,
        tempC = tempC,
        tempF = tempF,
        cloud = cloud,
        windKph = windKph,
        windMph = windMph,
        snowCm = snowCm,
        humidity = humidity,
        dewpointF = dewpointF,
        willItRain = willItRain,
        uv = uv,
        heatindexF = heatindexF,
        dewpointC = dewpointC,
        isDay = isDay,
        precipIn = precipIn,
        heatindexC = heatindexC,
        windDir = windDir,
        gustMph = gustMph,
        pressureIn = pressureIn,
        chanceOfRain = chanceOfRain,
        gustKph = gustKph,
        precipMm = precipMm,
        condition = condition?.toDomain(),
        willItSnow = willItSnow,
        visKm = visKm,
        timeEpoch = timeEpoch,
        time = time,
        chanceOfSnow = chanceOfSnow,
        pressureMb = pressureMb,
        visMiles = visMiles
    )

private fun Location.toDomain(): LocationDomain =
    LocationDomain(
        localtime = localtime,
        country = country,
        localtimeEpoch = localtimeEpoch,
        name = name,
        lon = lon,
        region = region,
        lat = lat,
        tzId = tzId
    )