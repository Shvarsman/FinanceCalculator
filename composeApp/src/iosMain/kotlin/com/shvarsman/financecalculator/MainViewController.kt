package com.shvarsman.financecalculator

import androidx.compose.ui.window.ComposeUIViewController
import com.shvarsman.financecalculator.ui.CalculatorScreen
import com.shvarsman.financecalculator.viewmodel.CalculatorViewModel

fun MainViewController() = ComposeUIViewController {
    CalculatorScreen(CalculatorViewModel())
}