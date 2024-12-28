package com.example.common.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.common.ui.theme.Neutral3
import com.example.common.ui.theme.Purple1

@Preview(showBackground = true)
@Composable
fun MyBottomSheetTopBar(
    selectedTabIndex: Int = 0,
    onTabSelected: (Int) -> Unit = {}
) {
    val tabs = listOf("Hourly Forecast", "Weekly Forecast")

    TabRow(
        selectedTabIndex = selectedTabIndex,
        containerColor = Color.Transparent,
        contentColor = Color.Transparent,
        divider = {},
        indicator = { tabPositions ->
            if (selectedTabIndex < tabPositions.size) {
                Row(
                    modifier = Modifier
                        .tabIndicatorOffset(tabPositions[selectedTabIndex])
                        .fillMaxSize()
                ) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.Bottom)
                            .fillMaxWidth()
                            .height(2.dp)
                            .background(
                                brush = Brush.linearGradient(
                                    colors = listOf(
                                        Color.Transparent,
                                        Purple1,
                                        Color.Transparent
                                    ),
                                )
                            )
                    )
                }
            }
        }
    ) {
        tabs.forEachIndexed { index, title ->
            Tab(
                selected = selectedTabIndex == index,
                onClick = { onTabSelected(index) },
                text = {
                    Text(
                        text = title,
                        color = Neutral3,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            )
        }
    }
}