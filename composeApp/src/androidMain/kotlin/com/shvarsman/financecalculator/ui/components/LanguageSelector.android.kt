package com.shvarsman.financecalculator.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.shvarsman.financecalculator.model.Language

@OptIn(ExperimentalMaterial3Api::class)
@Composable
actual fun LanguageSelector(
    currentLang: Language,
    onSelect: (Language) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        SingleChoiceSegmentedButtonRow {
            Language.entries.forEachIndexed { index, lang ->
                SegmentedButton(
                    shape = SegmentedButtonDefaults.itemShape(index, Language.entries.size),
                    onClick = { onSelect(lang) },
                    selected = currentLang == lang,
                    label = {
                        Text(when (lang) {
                            Language.RU -> "RU"
                            Language.EN -> "EN"
                            Language.BE -> "BE"
                        })
                    }
                )
            }
        }
    }
}