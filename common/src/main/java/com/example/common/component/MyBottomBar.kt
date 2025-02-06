package com.example.common.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.common.ui.theme.Purple1
import com.example.common.ui.theme.Purple2

@Preview(showBackground = true)
@Composable
fun MyBottomBar() {
    var selectedItem by remember { mutableIntStateOf(0) }

    val navigationItems = listOf(
        "Home" to Icons.Filled.Home,
        "Search" to Icons.Filled.Search,
        "Settings" to Icons.Filled.Settings
    )

    Box(modifier = Modifier.fillMaxWidth()) {
        BottomAppBar(
            containerColor = Purple1,
            contentColor = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
        ) {
            navigationItems.forEachIndexed { index, (label, icon) ->
                NavigationBarItem(
                    icon = { Icon(imageVector = icon, contentDescription = label) },
                    selected = selectedItem == index,
                    onClick = { selectedItem = index },
                    colors = NavigationBarItemDefaults.colors(indicatorColor = Color.Transparent),
                    label = { Text(label) },
                    modifier = Modifier.weight(1f)
                )
            }
        }

        // Floating Action Button di tengah
        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.Center)
                .offset(y = (-48).dp), // Menggeser ke atas
            onClick = {},
            containerColor = Purple2
        ) {
            Icon(
                imageVector = navigationItems[1].second,
                contentDescription = navigationItems[1].first,
                tint = Color.White
            )
        }
    }
}