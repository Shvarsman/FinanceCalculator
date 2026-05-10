package com.shvarsman.financecalculator.logic

import com.shvarsman.financecalculator.model.Compounding
import com.shvarsman.financecalculator.model.CalculationResult
import kotlin.math.pow

object FinancialCalculator {

    fun calculate(
        principal: Double,
        annualRatePercent: Double,
        years: Int,
        compounding: Compounding
    ): CalculationResult {
        val rate = annualRatePercent / 100.0
        val n = compounding.timesPerYear.toDouble()
        val totalPeriods = n * years
        val finalAmount = principal * (1 + rate / n).pow(totalPeriods)
        val totalProfit = finalAmount - principal

        val yearlyBalances = mutableListOf<Double>()
        for (year in 1..years) {
            val periodsPassed = n * year
            val balance = principal * (1 + rate / n).pow(periodsPassed)
            yearlyBalances.add(balance)
        }

        return CalculationResult(finalAmount, totalProfit, yearlyBalances)
    }
}