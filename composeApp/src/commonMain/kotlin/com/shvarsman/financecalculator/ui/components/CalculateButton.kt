package com.shvarsman.financecalculator.ui.components

import androidx.compose.runtime.Composable

@Composable
expect fun CalculateButton(
    onClick: () -> Unit,
    text: String,
    enabled: Boolean
)