package com.shvarsman.financecalculator.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@Composable
actual fun TermField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    enabled: Boolean
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(label) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            enabled = enabled
        )
        Spacer(Modifier.height(4.dp))
        Text("Перетащите ползунок", style = MaterialTheme.typography.labelSmall)
        Slider(
            value = value.toFloatOrNull() ?: 1f,
            onValueChange = { newValue ->
                onValueChange(newValue.roundToInt().toString())
            },
            valueRange = 1f..100f,
            steps = 0,
            enabled = enabled,
            modifier = Modifier.fillMaxWidth()
        )
    }
}