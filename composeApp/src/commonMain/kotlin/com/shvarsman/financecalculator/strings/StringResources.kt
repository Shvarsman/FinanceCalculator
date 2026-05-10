package com.shvarsman.financecalculator.strings

import com.shvarsman.financecalculator.model.Language

data class Strings(
    val principalLabel: String,
    val rateLabel: String,
    val yearsLabel: String,
    val compoundingLabel: String,
    val calculateButton: String,
    val resultFinalAmount: String,
    val resultTotalProfit: String,
    val graphTitle: String
)

fun getStrings(lang: Language): Strings = when (lang) {
    Language.RU -> Strings(
        principalLabel = "Начальная сумма",
        rateLabel = "Годовая ставка (%)",
        yearsLabel = "Срок (лет)",
        compoundingLabel = "Капитализация",
        calculateButton = "Рассчитать",
        resultFinalAmount = "Итоговая сумма",
        resultTotalProfit = "Прибыль",
        graphTitle = "Рост капитала"
    )
    Language.EN -> Strings(
        principalLabel = "Principal amount",
        rateLabel = "Annual rate (%)",
        yearsLabel = "Term (years)",
        compoundingLabel = "Compounding",
        calculateButton = "Calculate",
        resultFinalAmount = "Final amount",
        resultTotalProfit = "Profit",
        graphTitle = "Capital growth"
    )
    Language.BE -> Strings(
        principalLabel = "Пачатковая сума",
        rateLabel = "Гадавая стаўка (%)",
        yearsLabel = "Тэрмін (гадоў)",
        compoundingLabel = "Капіталізацыя",
        calculateButton = "Разлічыць",
        resultFinalAmount = "Выніковая сума",
        resultTotalProfit = "Прыбытак",
        graphTitle = "Рост капіталу"
    )
}