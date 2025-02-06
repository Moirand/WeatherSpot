package com.example.weatherspot.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.common.component.MyForecastCard
import com.example.domain.model.HourItemDomain
import com.example.weatherspot.utils.convertDoubleToIntOrDouble
import java.time.LocalDateTime

@SuppressLint("NewApi")
@Preview(showBackground = true)
@Composable
fun HourlyForecast(
    listHourlyForecast: List<HourItemDomain?> = listOf()
) {
    LazyRow(
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(24) { index ->
            MyForecastCard(
                time = index,
                icon = com.example.common.R.drawable.img_cloudy_windy_night,
                temperature = convertDoubleToIntOrDouble(listHourlyForecast[index]!!.tempC!!).toString(),
                isCurrent = index == LocalDateTime.now().hour
            )
        }
    }
}