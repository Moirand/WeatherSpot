package com.example.weatherspot.home

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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

@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
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
        sheetContent = { BottomSheetContent() },
        sheetContainerColor = Color(0xFF34326A),
        content = { MainContent(bottomSheetState) }
    )
}

@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContent(
    bottomSheetState: SheetState = rememberStandardBottomSheetState()
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
            Spacer(modifier = Modifier.height(48.dp))
            Text(
                text = "Montreal",
                color = Neutral1,
                style = MaterialTheme.typography.headlineLarge
            )
            Text(
                text = "19°",
                color = Neutral1,
                fontSize = 56.sp,
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontWeight = FontWeight.Thin
                )
            )
            Text(
                text = "Mostly Clear",
                color = Neutral3,
                fontSize = 20.sp,
                style = MaterialTheme.typography.bodySmall
            )
            Row {
                Text(
                    text = "H:24°",
                    color = Neutral1,
                    fontSize = 20.sp,
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontWeight = FontWeight.SemiBold
                    )
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "L:16°",
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
                text = "Montreal",
                color = Neutral1,
                style = MaterialTheme.typography.headlineLarge
            )
            Row {
                Text(
                    text = "19°",
                    color = Neutral3,
                    fontSize = 20.sp,
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Mostly Clear",
                    color = Neutral3,
                    fontSize = 20.sp,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomSheetContent() {
    Column(modifier = Modifier.height(624.dp)) {
        var selectedTabIndex by remember { mutableIntStateOf(0) }

        MyBottomSheetTopBar(
            selectedTabIndex = selectedTabIndex,
            onTabSelected = { selectedTabIndex = it }
        )

        when (selectedTabIndex) {
            0 -> HourlyForecast()
            1 -> HourlyForecast()
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                MyDetailWeatherCard(
                    icon = R.drawable.ic_humidity,
                    title = "UV Index",
                    detail = "4 Moderate"
                )
            }
            item {
                MyDetailWeatherCard(
                    icon = R.drawable.ic_humidity,
                    title = "Wind",
                    detail = "9.7 Km/h"
                )
            }
            item {
                MyDetailWeatherCard(
                    icon = R.drawable.ic_humidity,
                    title = "Feels Like",
                    detail = "19°",
                    extraDetail = "Similar to the actual temperature"
                )
            }
            item {
                MyDetailWeatherCard(
                    icon = R.drawable.ic_visibility_on,
                    title = "Visibility",
                    detail = "8 Km"
                )
            }
            item {
                MyDetailWeatherCard(
                    icon = R.drawable.ic_humidity,
                    title = "Sunrise",
                    detail = "5:28 AM",
                    extraDetail = "Sunset: 7:28 PM"
                )
            }
            item {
                MyDetailWeatherCard(
                    icon = R.drawable.ic_humidity,
                    title = "Rainfall",
                    detail = "1.8 mm in last hour",
                    extraDetail = "1.2 mm expected in next 24 hour"
                )
            }
            item {
                MyDetailWeatherCard(
                    icon = R.drawable.ic_humidity,
                    title = "Humidity",
                    detail = "90%",
                    extraDetail = "The dew point is 17 right now"
                )
            }
            item {
                MyDetailWeatherCard(
                    icon = R.drawable.ic_humidity,
                    title = "Pressure",
                    detail = "90%",
                    extraDetail = "The dew point is 17 right now"
                )
            }
        }
    }
}