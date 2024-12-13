package com.example.common.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.common.ui.theme.Neutral1
import com.example.common.ui.theme.Purple1

@Composable
fun MyButton(
    text: String,
    action: () -> Unit
) {
    Button(
        onClick = action,
        shape = RoundedCornerShape(10.dp),
        colors = ButtonColors(
            containerColor = Purple1,
            contentColor = Neutral1,
            disabledContainerColor = Purple1,
            disabledContentColor = Neutral1
        ),
        contentPadding = PaddingValues(vertical = 16.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold
            )
        )
    }
}