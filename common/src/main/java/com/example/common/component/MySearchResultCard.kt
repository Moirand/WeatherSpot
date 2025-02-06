package com.example.common.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.common.ui.theme.Neutral3

@Preview(showBackground = true)
@Composable
fun MySearchResultCard(
    city: String = "",
    region: String = "",
    country: String = "",
    lat: String = "",
    long: String = "",
    onClick: () -> Unit = {}
) {
    Card(
        shape  = CardDefaults.shape,
        colors  = CardDefaults.cardColors(),
        elevation  = CardDefaults.cardElevation(),
        border = BorderStroke(width = .5.dp, color = Neutral3),
        modifier = Modifier.padding(8.dp)
    ) {
        Row(){
            Text(
                text = city
            )
            Text(
                text = country
            )
        }
        Row(){
            Text(
                text = "Lat: $lat, Long: $long"
            )
            Text(
                text = region
            )
        }
    }
}