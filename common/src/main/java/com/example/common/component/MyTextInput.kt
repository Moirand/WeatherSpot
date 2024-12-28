package com.example.common.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.common.R

@Composable
fun MyTextInput(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    icon: Int? = null,
    isHideText: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    var textVisibility by remember { mutableStateOf(false) }

    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(placeholder) },
        singleLine = true,
        shape = RoundedCornerShape(10.dp),
        visualTransformation = if (isHideText && !textVisibility) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        prefix = {
            if (icon != null) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = null,
                    modifier = Modifier.padding(end = 8.dp)
                )
            }
        },
        trailingIcon = {
            if (value.isNotEmpty()) {
                if (isHideText) {
                    IconButton(
                        onClick = { textVisibility = !textVisibility }
                    ) {
                        Icon(
                            painter = if (textVisibility) painterResource(id = R.drawable.ic_visibility_on)
                            else painterResource(id = R.drawable.ic_visibility_off),
                            contentDescription = if (textVisibility) "Hide password" else "Show password"
                        )
                    }
                } else {
                    IconButton(
                        onClick = { onValueChange("") }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = "Clear input"
                        )
                    }
                }
            }
        },
        modifier = modifier.fillMaxWidth()
    )
}