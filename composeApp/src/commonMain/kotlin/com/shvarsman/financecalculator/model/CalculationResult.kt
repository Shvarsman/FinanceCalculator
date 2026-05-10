package com.shvarsman.financecalculator.model

import kotlinx.serialization.Serializable

@Serializable
data class CalculationResult(
    val finalAmount: Double,
    val totalProfit: Double,
    val yearlyBalances: List<Double>
)