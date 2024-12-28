package com.example.common.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.common.ui.theme.Neutral1
import com.example.common.ui.theme.Purple2
import com.example.common.ui.theme.WeatherSpotTheme

@Composable
fun MyForecastCard(
    time: Int,
    icon: Int,
    temperature: Int,
    isCurrent: Boolean
) {
    ElevatedCard(
        shape = RoundedCornerShape(50.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = if (isCurrent) Color(0xFF48319D) else Color(0xFF34326A)
        ),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 6.dp),
        modifier = Modifier
            .size(width = 64.dp, height = 144.dp)
            .border(1.dp, Purple2.copy(alpha = 0.5F), RoundedCornerShape(50.dp))
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(vertical = 16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = if (isCurrent) "Now" else "$time AM",
                style = MaterialTheme.typography.bodySmall.copy(
                    color = Neutral1,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            )
            Image(
                painter = painterResource(id = icon),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .weight(1f)
            )
            Text(
                text = "$temperature°",
                style = MaterialTheme.typography.bodySmall.copy(
                    color = Neutral1,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MyForecastCard1Preview() {
    WeatherSpotTheme {
        MyForecastCard(
            time = 12,
            icon = com.example.common.R.drawable.img_cloudy_windy_night,
            temperature = 19,
            isCurrent = true
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MyForecastCard2Preview() {
    WeatherSpotTheme {
        MyForecastCard(
            time = 12,
            icon = com.example.common.R.drawable.img_cloudy_windy_night,
            temperature = 19,
            isCurrent = false
        )
    }
}