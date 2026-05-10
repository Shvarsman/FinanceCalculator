package com.shvarsman.financecalculator.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.shvarsman.financecalculator.model.Compounding

@Composable
actual fun CapitalizationSelector(
    current: Compounding,
    onSelect: (Compounding) -> Unit,
    label: String
) {
    var expanded by remember { mutableStateOf(false) }
    Column {
        Text(label, style = MaterialTheme.typography.labelMedium)
        Box {
            OutlinedButton(onClick = { expanded = true }) {
                Text(when (current) {
                    Compounding.MONTHLY -> "Ежемесячно"
                    Compounding.QUARTERLY -> "Ежеквартально"
                    Compounding.ANNUALLY -> "Ежегодно"
                })
            }
            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                Compounding.entries.forEach { comp ->
                    DropdownMenuItem(
                        text = { Text(when (comp) {
                            Compounding.MONTHLY -> "Ежемесячно"
                            Compounding.QUARTERLY -> "Ежеквартально"
                            Compounding.ANNUALLY -> "Ежегодно"
                        }) },
                        onClick = {
                            onSelect(comp)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}