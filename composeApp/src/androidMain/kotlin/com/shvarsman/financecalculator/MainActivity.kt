package com.shvarsman.financecalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.shvarsman.financecalculator.cache.Cache
import com.shvarsman.financecalculator.ui.CalculatorScreen
import com.shvarsman.financecalculator.viewmodel.CalculatorViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Cache.appContext = applicationContext
        enableEdgeToEdge()
        setContent {
            CalculatorScreen(viewModel = CalculatorViewModel())
        }
    }
}