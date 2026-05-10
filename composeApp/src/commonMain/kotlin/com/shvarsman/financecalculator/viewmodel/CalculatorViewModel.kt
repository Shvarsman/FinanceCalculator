package com.shvarsman.financecalculator.viewmodel

import com.shvarsman.financecalculator.cache.Cache
import com.shvarsman.financecalculator.logic.FinancialCalculator
import com.shvarsman.financecalculator.logic.InputValidator
import com.shvarsman.financecalculator.model.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

data class CalculatorUiState(
    val principal: String = "100000",
    val rate: String = "5",
    val years: String = "5",
    val compounding: Compounding = Compounding.MONTHLY,
    val result: CalculationResult? = null,
    val errorMessage: String? = null,
    val isLoading: Boolean = false,
    val language: Language = Language.RU
)

class CalculatorViewModel(
    private val cache: Cache = Cache(),
    private val scope: CoroutineScope = MainScope()
) {
    private val _state = MutableStateFlow(CalculatorUiState())
    val state: StateFlow<CalculatorUiState> = _state.asStateFlow()

    init {
        loadSavedData()
    }

    fun onPrincipalChange(value: String) { _state.update { it.copy(principal = value) } }
    fun onRateChange(value: String) { _state.update { it.copy(rate = value) } }
    fun onYearsChange(value: String) { _state.update { it.copy(years = value) } }
    fun onCompoundingChange(newCompounding: Compounding) {
        _state.update { it.copy(compounding = newCompounding) }
    }
    fun onLanguageChange(lang: Language) {
        _state.update { it.copy(language = lang) }
    }

    fun calculate() {
        val curState = _state.value
        val validation = InputValidator.validate(curState.principal, curState.rate, curState.years)
        if (!validation.isValid) {
            _state.update { it.copy(errorMessage = validation.message) }
            return
        }

        scope.launch {
            try {
                _state.update { it.copy(isLoading = true, errorMessage = null) }
                delay(100)
                val p = curState.principal.toDouble()
                val r = curState.rate.toDouble()
                val y = curState.years.toInt()
                val result = FinancialCalculator.calculate(p, r, y, curState.compounding)
                _state.update { it.copy(result = result, isLoading = false) }
                saveCurrentData()
            } catch (e: Exception) {
                _state.update { it.copy(isLoading = false, errorMessage = "Ошибка вычислений: ${e.message}") }
            }
        }
    }

    private suspend fun saveCurrentData() {
        val data = InputData(
            principal = _state.value.principal.toDoubleOrNull() ?: 100000.0,
            annualRate = _state.value.rate.toDoubleOrNull() ?: 5.0,
            years = _state.value.years.toIntOrNull() ?: 5,
            compounding = _state.value.compounding
        )
        val jsonData = Json.encodeToString(data)
        cache.save("last_input", jsonData)
        _state.value.result?.let {
            val jsonResult = Json.encodeToString(it)
            cache.save("last_result", jsonResult)
        }
    }

    private fun loadSavedData() {
        scope.launch {
            val inputStr = cache.load("last_input")
            if (inputStr != null) {
                try {
                    val inputData = Json.decodeFromString<InputData>(inputStr)
                    _state.update {
                        it.copy(
                            principal = doubleToPrettyString(inputData.principal),
                            rate = doubleToPrettyString(inputData.annualRate),
                            years = inputData.years.toString(),
                            compounding = inputData.compounding
                        )
                    }
                } catch (_: Exception) {}
            }
            val resultStr = cache.load("last_result")
            if (resultStr != null) {
                try {
                    val result = Json.decodeFromString<CalculationResult>(resultStr)
                    _state.update { it.copy(result = result) }
                } catch (_: Exception) {}
            }
        }
    }

    // Убирает .0 у целых чисел, например 5.0 -> "5"
    private fun doubleToPrettyString(d: Double): String {
        return if (d == d.toLong().toDouble()) d.toLong().toString() else d.toString()
    }

    fun onCleared() {
        scope.cancel()
    }
}