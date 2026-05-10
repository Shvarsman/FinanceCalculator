package com.shvarsman.financecalculator.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.shvarsman.financecalculator.model.Compounding

@OptIn(ExperimentalMaterial3Api::class)
@Composable
actual fun CapitalizationSelector(
    current: Compounding,
    onSelect: (Compounding) -> Unit,
    label: String
) {
    Column {
        Text(label, style = MaterialTheme.typography.labelMedium)
        Spacer(Modifier.height(4.dp))
        SingleChoiceSegmentedButtonRow(modifier = Modifier.fillMaxWidth()) {
            Compounding.entries.forEachIndexed { index, compounding ->
                SegmentedButton(
                    shape = SegmentedButtonDefaults.itemShape(index, Compounding.entries.size),
                    onClick = { onSelect(compounding) },
                    selected = current == compounding,
                    label = {
                        Text(when (compounding) {
                            Compounding.MONTHLY -> "Ежемесячно"
                            Compounding.QUARTERLY -> "Ежеквартально"
                            Compounding.ANNUALLY -> "Ежегодно"
                        })
                    }
                )
            }
        }
    }
}