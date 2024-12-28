package com.example.common.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.common.ui.theme.Neutral1
import com.example.common.ui.theme.Neutral3
import com.example.common.ui.theme.Neutral9
import java.util.Locale

@Preview(showBackground = true)
@Composable
fun MyDetailWeatherCard(
    icon: Int = com.example.common.R.drawable.ic_humidity,
    title: String = "Humidity",
    detail: String = "90%",
    extraDetail: String? = null
) {
    OutlinedCard(
        shape = RoundedCornerShape(24.dp),
        border = BorderStroke(1.dp, Neutral1.copy(alpha = 0.2F)),
        colors = CardDefaults.outlinedCardColors(containerColor = Neutral9.copy(alpha = 0.2F)),
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
        ) {
            CardTitle(icon, title)
            Text(
                text = detail,
                color = Neutral1,
                style = MaterialTheme.typography.headlineLarge
            )
            Spacer(modifier = Modifier.weight(1F))
            Text(
                text = extraDetail ?: "",
                color = Neutral1,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun CardTitle(
    icon: Int,
    title: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = Neutral3,
            modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = title.uppercase(Locale.getDefault()),
            color = Neutral3,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}