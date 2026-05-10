package com.shvarsman.financecalculator.ui.components

import androidx.compose.runtime.Composable

@Composable
expect fun RateField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    enabled: Boolean
)