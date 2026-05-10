package com.shvarsman.financecalculator.logic

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class InputValidatorTest {

    @Test
    fun `test valid input`() {
        val result = InputValidator.validate("100000", "5.5", "10")
        assertTrue(result.isValid)
        assertTrue(result.message == null)
    }

    @Test
    fun `test negative principal`() {
        val result = InputValidator.validate("-1000", "5", "1")
        assertFalse(result.isValid)
        assertTrue(result.message!!.contains("Сумма"))
    }

    @Test
    fun `test rate out of range`() {
        val result = InputValidator.validate("1000", "101", "5")
        assertFalse(result.isValid)
        assertTrue(result.message!!.contains("ставка"))
    }

    @Test
    fun `test years out of range`() {
        val result = InputValidator.validate("1000", "5", "0")
        assertFalse(result.isValid)
    }

    @Test
    fun `test non-numeric input`() {
        val result = InputValidator.validate("abc", "5", "5")
        assertFalse(result.isValid)
    }
}