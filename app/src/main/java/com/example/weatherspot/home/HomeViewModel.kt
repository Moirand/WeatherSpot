package com.example.weatherspot.home

import android.location.Location
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.UiState
import com.example.domain.model.response.ForecastResponseDomain
import com.example.domain.repository.WeatherRepository
import com.example.weatherspot.utils.DUMMY_COORDINATE
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class HomeViewModel(
    private val weatherRepository: WeatherRepository
) : ViewModel() {
    private val _locationCoordinate = MutableStateFlow(DUMMY_COORDINATE)

    fun setLocation(location: Location) {
        _locationCoordinate.update { Pair(location.latitude, location.longitude) }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    val forecastState: StateFlow<UiState<ForecastResponseDomain>> = _locationCoordinate
        .flatMapConcat { (lat, long) ->
            weatherRepository.getSevenDaysForecast(lat, long)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = UiState.Loading()
        )
}