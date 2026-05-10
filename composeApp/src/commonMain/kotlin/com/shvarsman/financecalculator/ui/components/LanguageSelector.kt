package com.shvarsman.financecalculator.ui.components

import androidx.compose.runtime.Composable
import com.shvarsman.financecalculator.model.Language

@Composable
expect fun LanguageSelector(
    currentLang: Language,
    onSelect: (Language) -> Unit
)