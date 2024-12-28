package com.example.weatherspot.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.common.component.MyForecastCard

@Preview(showBackground = true)
@Composable
fun HourlyForecast() {
    LazyRow(
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(24) { index ->
            MyForecastCard(
                time = index + 1,
                icon = com.example.common.R.drawable.img_cloudy_windy_night,
                temperature = 19,
                isCurrent = true
            )
        }
    }
}