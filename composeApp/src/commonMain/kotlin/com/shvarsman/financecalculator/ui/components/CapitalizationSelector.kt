package com.shvarsman.financecalculator.ui.components

import androidx.compose.runtime.Composable
import com.shvarsman.financecalculator.model.Compounding

@Composable
expect fun CapitalizationSelector(
    current: Compounding,
    onSelect: (Compounding) -> Unit,
    label: String
)