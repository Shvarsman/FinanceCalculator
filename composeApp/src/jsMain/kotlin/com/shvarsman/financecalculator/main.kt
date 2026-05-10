package com.shvarsman.financecalculator

import com.shvarsman.financecalculator.ui.CalculatorScreen
import com.shvarsman.financecalculator.viewmodel.CalculatorViewModel
import org.jetbrains.compose.web.renderComposable

fun main() {
    renderComposable(rootElementId = "root") {
        CalculatorScreen(CalculatorViewModel())
    }
}