package com.example.weatherspot.home

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.common.R
import com.example.common.component.MyBottomSheetTopBar
import com.example.common.component.MyDetailWeatherCard
import com.example.common.ui.theme.Neutral1
import com.example.common.ui.theme.Neutral3
import com.example.domain.UiState
import com.example.domain.model.response.ForecastResponseDomain
import com.example.weatherspot.utils.LocationHelper
import org.koin.androidx.compose.koinViewModel

@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel(),
) {
    val context = LocalContext.current
    val locationHelper = remember { LocationHelper(context) }

    val locationState by remember { locationHelper.locationState }.collectAsState()
    val weatherState by remember { viewModel.forecastState }.collectAsState()

    var locationWeather by remember { mutableStateOf(ForecastResponseDomain()) }

    // Popup aktivasi gps
    val gpsLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.StartIntentSenderForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) { // Jika memilih "Oke"
            locationHelper.startLocationUpdates()
        }
    }

    // Popup izin lokasi
    val locationLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val allGranted = permissions.values.all { it }
        if (allGranted) {
            locationHelper.checkAndHandleGpsStatus(
                gpsLauncher = gpsLauncher,
                onGpsOn = { locationHelper.startLocationUpdates() }
            )
        }
    }

    LaunchedEffect(Unit) {
        locationHelper.getLastLocation()

        if (locationHelper.isLocationPermissionGranted()) {
            locationHelper.checkAndHandleGpsStatus(
                gpsLauncher = gpsLauncher,
                onGpsOn = { locationHelper.startLocationUpdates() }
            )
        } else {
            locationHelper.requestLocationPermission(locationLauncher)
        }
    }

    LaunchedEffect(locationState) {
        when (locationState) {
            is UiState.Error -> Log.e("HomeScreen", "HomeScreen() locationState: ${locationState.message}")
            is UiState.Loading -> {}
            is UiState.Success -> locationState.data?.let { currentLocation ->
                viewModel.setLocation(currentLocation)
            }
        }
    }

    LaunchedEffect(weatherState) {
        when (weatherState) {
            is UiState.Error -> Log.d("HomeScreen", "HomeScreen() weatherState: ${weatherState.message}")
            is UiState.Loading -> {}
            is UiState.Success -> weatherState.data?.let { currentLocationWeather ->
                locationWeather = currentLocationWeather
            }
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            locationHelper.stopLocationUpdates()
        }
    }

    // BottomSheet setup
    val bottomSheetState = rememberStandardBottomSheetState(
        initialValue = SheetValue.PartiallyExpanded,
        confirmValueChange = { sheetValue ->
            when (sheetValue) {
                SheetValue.Expanded -> true // Izinkan diperluas
                SheetValue.PartiallyExpanded -> true // Tetap pada peek height
                SheetValue.Hidden -> false // Tolak state ditutup
            }
        }
    )

    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = bottomSheetState
    )

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = 386.dp,
        sheetShape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp),
        sheetContent = { BottomSheetContent(locationWeather) },
        sheetContainerColor = Color(0xFF34326A),
        content = { MainContent(bottomSheetState, locationWeather) }
    )
}

@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MainContent(
    bottomSheetState: SheetState = rememberStandardBottomSheetState(),
    locationWeather: ForecastResponseDomain = ForecastResponseDomain()
) {
    val alpha1 by animateFloatAsState(
        animationSpec = tween(
            durationMillis = 1000,
            delayMillis = 0,
            easing = LinearOutSlowInEasing
        ),
        targetValue = when (bottomSheetState.currentValue) {
            SheetValue.Expanded -> 0f
            SheetValue.PartiallyExpanded -> 1f
            SheetValue.Hidden -> 0f
        }, label = ""
    )

    val alpha2 by animateFloatAsState(
        animationSpec = tween(
            durationMillis = 1000,
            delayMillis = 0,
            easing = LinearOutSlowInEasing
        ),
        targetValue = when (bottomSheetState.currentValue) {
            SheetValue.Expanded -> 1f
            SheetValue.PartiallyExpanded -> 0f
            SheetValue.Hidden -> 0f
        }, label = ""
    )

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.img_home_screen),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Image(
            painter = painterResource(id = R.drawable.img_house),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .alpha(alpha1)
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = locationWeather.location?.name ?: "City",
                color = Neutral1,
                style = MaterialTheme.typography.headlineLarge
            )
            Text(
                text = "${locationWeather.current?.tempC}°",
                color = Neutral1,
                fontSize = 56.sp,
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.Thin
                )
            )
            Text(
                text = locationWeather.current?.condition?.text ?: "",
                color = Neutral3,
                fontSize = 20.sp,
                style = MaterialTheme.typography.bodySmall
            )
            Row {
                Text(
                    text = "H:${locationWeather.forecast?.forecastday?.get(0)?.day?.maxtempC}°C",
                    color = Neutral1,
                    fontSize = 20.sp,
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontWeight = FontWeight.SemiBold
                    )
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "L:${locationWeather.forecast?.forecastday?.get(0)?.day?.mintempC}°C",
                    color = Neutral1,
                    fontSize = 20.sp,
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .alpha(alpha2)
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = locationWeather.location?.name ?: "City",
                color = Neutral1,
                style = MaterialTheme.typography.headlineLarge
            )
            Row {
                Text(
                    text = "${locationWeather.current?.tempC}°",
                    color = Neutral3,
                    fontSize = 20.sp,
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = locationWeather.current?.condition?.text ?: "",
                    color = Neutral3,
                    fontSize = 20.sp,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@SuppressLint("NewApi")
@Preview(showBackground = true)
@Composable
private fun BottomSheetContent(
    locationWeather: ForecastResponseDomain = ForecastResponseDomain()
) {
    Column(modifier = Modifier.height(624.dp)) {
        var selectedTabIndex by remember { mutableIntStateOf(0) }

        MyBottomSheetTopBar(
            selectedTabIndex = selectedTabIndex,
            onTabSelected = { selectedTabIndex = it }
        )

        locationWeather.forecast?.forecastday?.get(0)?.hour?.let {
            when (selectedTabIndex) {
                0 -> HourlyForecast(it)
//            1 -> HourlyForecast(locationWeather.forecast?.forecastday?.get(0)?.hour)
            }
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                MyDetailWeatherCard(
                    icon = R.drawable.ic_uv,
                    title = "UV Index",
                    detail = "${locationWeather.current?.uv}"
                )
            }
            item {
                MyDetailWeatherCard(
                    icon = R.drawable.ic_wind,
                    title = "Wind",
                    detail = "${locationWeather.current?.windMph} Mp/h"
                )
            }
            item {
                MyDetailWeatherCard(
                    icon = R.drawable.ic_thermometer,
                    title = "Feels Like",
                    detail = "${locationWeather.current?.feelslikeC}°C",
                    extraDetail = "Similar to the actual temperature"
                )
            }
            item {
                MyDetailWeatherCard(
                    icon = R.drawable.ic_visibility_on,
                    title = "Visibility",
                    detail = "${locationWeather.current?.visKm} Km"
                )
            }
            item {
                MyDetailWeatherCard(
                    icon = R.drawable.ic_sunrise,
                    title = "Sunrise",
                    detail = "${locationWeather.forecast?.forecastday?.get(0)?.astro?.sunrise}",
                    extraDetail = "Sunset: ${locationWeather.forecast?.forecastday?.get(0)?.astro?.sunset}"
                )
            }
            item {
                MyDetailWeatherCard(
                    icon = R.drawable.ic_precipitation,
                    title = "Precipitation",
                    detail = "${locationWeather.current?.precipMm} Mm"
                )
            }
            item {
                MyDetailWeatherCard(
                    icon = R.drawable.ic_humidity,
                    title = "Humidity",
                    detail = "${locationWeather.current?.humidity}"
                )
            }
            item {
                MyDetailWeatherCard(
                    icon = R.drawable.ic_pressure,
                    title = "Pressure",
                    detail = "${locationWeather.current?.pressureMb} Mb"
                )
            }
        }
    }
}