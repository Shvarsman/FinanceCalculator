package com.shvarsman.financecalculator.ui.components

import androidx.compose.runtime.Composable

@Composable
expect fun TermField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    enabled: Boolean
)