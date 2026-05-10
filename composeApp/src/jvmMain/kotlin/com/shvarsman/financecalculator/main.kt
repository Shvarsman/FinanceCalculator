package com.shvarsman.financecalculator

import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.shvarsman.financecalculator.ui.CalculatorScreen
import com.shvarsman.financecalculator.viewmodel.CalculatorViewModel

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Финансовый калькулятор",
        state = rememberWindowState(width = 400.dp, height = 700.dp)
    ) {
        CalculatorScreen(CalculatorViewModel())
    }
}