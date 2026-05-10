package com.shvarsman.financecalculator.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
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
        Spacer(Modifier.height(4.dp))
        Box {
            OutlinedButton(
                onClick = { expanded = true },
                modifier = Modifier.fillMaxWidth().border(1.dp, Color.Gray, RoundedCornerShape(4.dp)),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black)
            ) {
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