package com.shvarsman.financecalculator.ui.components

import androidx.compose.runtime.Composable

@Composable
expect fun AmountField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    enabled: Boolean
)