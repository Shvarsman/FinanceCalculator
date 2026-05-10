package com.shvarsman.financecalculator.logic

import com.shvarsman.financecalculator.model.Compounding
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class FinancialCalculatorTest {

    @Test
    fun `test monthly compounding`() {
        val result = FinancialCalculator.calculate(
            principal = 100000.0,
            annualRatePercent = 12.0,
            years = 1,
            compounding = Compounding.MONTHLY
        )
        // 100000 * (1 + 0.12/12)^12 ≈ 112682.50
        assertTrue(result.finalAmount > 112600 && result.finalAmount < 112700)
        assertEquals(1, result.yearlyBalances.size)
    }

    @Test
    fun `test zero rate returns same amount`() {
        val result = FinancialCalculator.calculate(
            principal = 50000.0,
            annualRatePercent = 0.0,
            years = 10,
            compounding = Compounding.ANNUALLY
        )
        assertEquals(50000.0, result.finalAmount)
        assertEquals(0.0, result.totalProfit)
    }

    @Test
    fun `test yearly compounding`() {
        val result = FinancialCalculator.calculate(
            principal = 1000.0,
            annualRatePercent = 10.0,
            years = 3,
            compounding = Compounding.ANNUALLY
        )
        // 1000 * 1.1^3 = 1331.0
        assertEquals(1331.0, result.finalAmount, 0.01)
        assertEquals(331.0, result.totalProfit, 0.01)
    }
}