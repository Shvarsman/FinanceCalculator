package com.shvarsman.financecalculator.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.shvarsman.financecalculator.model.Language

@Composable
actual fun LanguageSelector(
    currentLang: Language,
    onSelect: (Language) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Language.entries.forEach { lang ->
            FilterChip(
                selected = currentLang == lang,
                onClick = { onSelect(lang) },
                label = {
                    Text(when (lang) {
                        Language.RU -> "RU"
                        Language.EN -> "EN"
                        Language.BE -> "BE"
                    })
                },
                modifier = Modifier.padding(horizontal = 4.dp),
                border = BorderStroke(1.dp, Color.Gray),
                shape = RoundedCornerShape(4.dp)
            )
        }
    }
}