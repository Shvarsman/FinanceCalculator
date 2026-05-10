package com.shvarsman.financecalculator.logic

object InputValidator {
    data class ValidationResult(
        val isValid: Boolean,
        val message: String? = null
    )

    fun validate(principal: String, rate: String, years: String): ValidationResult {
        val principalValue = principal.toDoubleOrNull()
        val rateValue = rate.toDoubleOrNull()
        val yearsValue = years.toIntOrNull()

        if (principalValue == null || principalValue <= 0)
            return ValidationResult(false, "Сумма должна быть положительным числом")
        if (rateValue == null || rateValue < 0 || rateValue > 100)
            return ValidationResult(false, "Процентная ставка должна быть от 0 до 100")
        if (yearsValue == null || yearsValue <= 0 || yearsValue > 100)
            return ValidationResult(false, "Срок должен быть от 1 до 100 лет")
        return ValidationResult(true)
    }
}